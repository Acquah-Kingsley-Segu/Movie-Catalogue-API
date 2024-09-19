package com.kinglsey.movie_auth_service.controllers;

import com.kinglsey.movie_auth_service.dtos.AccountLoginPayload;
import com.kinglsey.movie_auth_service.dtos.AccountRegisterDto;
import com.kinglsey.movie_auth_service.dtos.UserPayload;
import com.kinglsey.movie_auth_service.services.AuthService;
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

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody AccountLoginPayload loginData){
        var authenticatedUser = authService.authenticateUser(loginData);
        if(authenticatedUser != null){
            return ResponseEntity.ok(authenticatedUser);
        }
        return ResponseEntity.status(401).build();
    }
}
