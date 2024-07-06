package com.kinglsey.movie_auth_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/movie-catalogue/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountRegisterDto> registerUser(@RequestBody UserPayload registerData){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createUserAccount(registerData));

    }
}
