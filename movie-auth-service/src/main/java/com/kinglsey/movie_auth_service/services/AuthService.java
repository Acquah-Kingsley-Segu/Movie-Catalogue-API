package com.kinglsey.movie_auth_service.services;

import com.kinglsey.movie_auth_service.*;
import com.kinglsey.movie_auth_service.configs.security.SecurityUserService;
import com.kinglsey.movie_auth_service.dtos.AccountLoginDto;
import com.kinglsey.movie_auth_service.dtos.AccountLoginPayload;
import com.kinglsey.movie_auth_service.dtos.AccountRegisterDto;
import com.kinglsey.movie_auth_service.dtos.UserPayload;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUserService securityUserService;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, SecurityUserService securityUserService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityUserService = securityUserService;
        this.jwtService = jwtService;
    }

    public AccountRegisterDto createUserAccount(UserPayload registerData) {
        Users newUser = new Users(registerData.username(), registerData.email(), passwordEncoder.encode(registerData.password()));
        newUser = userRepository.save(newUser);
        return new AccountRegisterDto(newUser.getUsername(), newUser.getEmail());
    }

    public AccountLoginDto authenticateUser(AccountLoginPayload loginData) {
        String userEmail = loginData.email();
        String password = loginData.password();

        UserDetails user = securityUserService.loadUserByUsername(userEmail);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        String token = jwtService.generateToken(user);
        return new AccountLoginDto(user.getUsername(), token);
    }

    public AccountLoginDto oauthSuccessHandler(OAuth2AuthenticationToken authentication){
        String username = authentication.getPrincipal().getAttribute("name");
        String email = authentication.getPrincipal().getAttribute("email");
        String password = username+"-"+email;

        var possibleUser = userRepository.findUsersByEmail(email);
        Users user = possibleUser.map(mappedUser -> {
            mappedUser.setEmail(email);
            mappedUser.setUsername(username);
            return mappedUser;
        }).orElseGet(() -> {
            Users newUser = new Users();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword(password);
            return newUser;
        });
        Users savedUser = userRepository.save(user);
        String token = jwtService.generateToken(securityUserService.loadUserByUsername(savedUser.getEmail()));
        return new AccountLoginDto(savedUser.getEmail(), token);
    }
}
