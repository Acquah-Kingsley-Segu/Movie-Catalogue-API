package com.kinglsey.movie_auth_service;

import com.kinglsey.movie_auth_service.configs.security.SecurityUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
}
