package com.kinglsey.movie_auth_service.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final MovieAuthenticationFilter movieAuthenticationFilter;

    public SecurityConfig(MovieAuthenticationFilter movieAuthenticationFilter) {
        this.movieAuthenticationFilter = movieAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorization -> {
                    authorization.requestMatchers(
                            "/api/v1/movie-catalogue/auth/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/swagger-ui.html",
                            "/webjars/**",
                            "/swagger-resources/**"
                    ).permitAll();
                    authorization.anyRequest().authenticated();
                })
                .addFilterAt(movieAuthenticationFilter, BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/api/v1/movie-catalogue/auth/oauth2/success", true));
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
