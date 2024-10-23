package com.kinglsey.movie_auth_service.controllers;

import com.kinglsey.movie_auth_service.dtos.AccountLoginPayload;
import com.kinglsey.movie_auth_service.dtos.AccountRegisterDto;
import com.kinglsey.movie_auth_service.dtos.UserPayload;
import com.kinglsey.movie_auth_service.services.AuthService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition(info = @Info(title = "Xclusive Movie API Authentication Microservice",
                                description = "Allows users perform account creation and authentication by providing personal details",
                                version = "v1.0.0",
                                contact = @Contact(name = "Acquah Kingsley Segu", email = "acquahkingsleysegu10@gmail.com"),
                                summary = "Manually account creation and authentication without third party intervention"
                               )
)
@RestController
@RequestMapping("/api/v1/movie-catalogue/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Registers new user",
            description = """
               This endpoint allows user create an account by providing:
                  1. username
                  2. email
                  3. password
            """,
            tags = { "Accounts" }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details need to create an account.")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "Account created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountRegisterDto.class)
                    )
            )
    )
    @PostMapping("/register")
    public ResponseEntity<AccountRegisterDto> registerUser(@RequestBody UserPayload registerData){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createUserAccount(registerData));
    }

    @Operation(
            summary = "Authenticate user",
            description = """
               This endpoint allows user to log into their by providing:
                  1. email
                  2. password
            """,
            tags = { "Accounts" }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Account log in credentials")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "Account created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountRegisterDto.class)
                    )
            )
    )
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody AccountLoginPayload loginData){
        var authenticatedUser = authService.authenticateUser(loginData);
        if(authenticatedUser != null){
            return ResponseEntity.ok(authenticatedUser);
        }
        return ResponseEntity.status(401).build();
    }
}
