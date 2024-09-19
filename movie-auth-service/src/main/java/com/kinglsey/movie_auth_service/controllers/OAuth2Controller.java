package com.kinglsey.movie_auth_service.controllers;

import com.kinglsey.movie_auth_service.UserRepository;
import com.kinglsey.movie_auth_service.Users;
import com.kinglsey.movie_auth_service.configs.security.SecurityUserService;
import com.kinglsey.movie_auth_service.dtos.AccountLoginDto;
import com.kinglsey.movie_auth_service.services.JwtService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final SecurityUserService securityUserService;

    public OAuth2Controller(UserRepository userRepository, JwtService jwtService, SecurityUserService securityUserService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.securityUserService = securityUserService;
    }

    @GetMapping("/oauth2/success")
    public AccountLoginDto demo(OAuth2AuthenticationToken authentication) {
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
        return new AccountLoginDto(email, token);
    }
}
