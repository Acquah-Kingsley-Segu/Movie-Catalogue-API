package com.kinglsey.movie_auth_service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String SECRET = "3e216cb1bba90db0a0aafff3be3b03002c5c3b9a59b17d347a7312b485e1329db628750d91811f4f73dc2ea73213558131c7beb1e26ca11fe946e3ccaec7b687e8a4bb70ab09f3dfc438cb60abc922aab45d3b9b334bee546d7fbac14ada59e6c36d4e72525e2a45898cf2cf87f902fc2c8b55c85c42b3a6539da6637fbb476a0133a0f704e538d9d61d4815e4abae0741e12f0376c80868b7c6b2ae68828389bb4a01adcc96114517f4b958475e39c52c7d140e1b5f74ab313108878a0c5fbdec6319c8a59c1cb09c34595b32292bc2467ead7b6e20688600a6799f01ea4af18af42b6bc1f0aeda5d61285e777f5493b41d1740ef6c646f9133074c01b2b8e6";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
