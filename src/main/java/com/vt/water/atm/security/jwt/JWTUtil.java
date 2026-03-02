package com.vt.water.atm.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class JWTUtil {

    // Token validity: 24 hours
    private static final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000;

    // Secret key (use env variable in production)
    private final SecretKey secretKey =
            Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey12".getBytes());

    // ==========================
    // Generate Token
    // ==========================
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(
                "roles",
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // ==========================
    // Extract Username
    // ==========================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ==========================
    // Extract Claims
    // ==========================
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ==========================
    // Token Validation
    // ==========================
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // ==========================
    // Expiration Check
    // ==========================
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
