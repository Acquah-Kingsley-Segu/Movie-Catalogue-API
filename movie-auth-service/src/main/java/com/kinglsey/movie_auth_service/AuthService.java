package com.kinglsey.movie_auth_service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users createUserAccount(UserDto registerData) {
        Users newUser = new Users(registerData.username(), registerData.email(), passwordEncoder.encode(registerData.password()));
        return userRepository.save(newUser);
    }
}
