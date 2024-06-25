package com.kinglsey.movie_auth_service;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/movie-catalogue/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Object registerUser(@RequestBody UserDto registerData){
        return authService.createUserAccount(registerData);
    }
}
