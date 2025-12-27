package com.example.ocm.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET = 
            "mysecretkeymysecretkeymysecretkey123456"; // MUST be 32+ chars

    private final byte[] keyBytes = SECRET.getBytes();

    // ----------- Generate JWT -----------
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000)) // 10 hrs
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();
    }

    // ----------- Validate JWT -----------
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ----------- Extract Email -----------
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
