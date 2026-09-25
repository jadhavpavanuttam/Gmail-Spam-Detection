package com.example.gmailfrauddetection.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class JwtService {
    private final SecretKey key;
    private final long expiration;
    private final long refreshExpiration;

    public JwtService(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration}") long e, @Value("${app.jwt.refresh-expiration}") long re) {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        expiration = e;
        refreshExpiration = re;
    }

    public String createAccessToken(String username, String role) {
        return build(username, role, expiration, "access");
    }

    public String createRefreshToken(String username) {
        return build(username, "USER", refreshExpiration, "refresh");
    }

    private String build(String u, String role, long exp, String type) {
        return Jwts.builder().subject(u).claim("role", role).claim("type", type).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + exp)).signWith(key).compact();
    }

    public String username(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean valid(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}