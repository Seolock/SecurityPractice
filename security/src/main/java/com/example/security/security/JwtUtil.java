package com.example.security.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


@Component
public class JwtUtil {


    private SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret}")String secret){
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(String username, List<String> roles) {
        long expirationTime = 1000 * 60 * 60;
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(new Date().getTime()+ expirationTime))
                .signWith(secretKey)
                .claim("username", username)
                .claim("role", roles.get(0))
                .compact();
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
}
