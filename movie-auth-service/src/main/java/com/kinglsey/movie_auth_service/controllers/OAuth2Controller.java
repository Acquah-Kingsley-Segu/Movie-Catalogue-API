package com.kinglsey.movie_auth_service.controllers;

import com.kinglsey.movie_auth_service.dtos.AccountLoginDto;
import com.kinglsey.movie_auth_service.services.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/movie-catalogue/auth")
public class OAuth2Controller {
    private final AuthService authService;

    public OAuth2Controller(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "OAuth Authentication and Authorization with Google",
            description = "This endpoint allows user create an account using a google associated account",
            tags = { "Accounts" }
    )
    @ApiResponses(@ApiResponse(responseCode = "204", description = "Google OAuth successful"))
    @GetMapping("/oauth/google")
    public void initiateGoogleOAuth(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:9091/oauth2/authorization/google");
    }

    @Hidden
    @GetMapping("/oauth2/success")
    public ResponseEntity<AccountLoginDto> demo(OAuth2AuthenticationToken authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.oauthSuccessHandler(authentication));
    }
}
