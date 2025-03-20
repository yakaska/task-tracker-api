package ru.yakaska.tasktrackerapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${app.jwt-secret}")
    private String secretKey;

    @Value("${app.jwt-expiration}")
    private int expirationTime;

    public String generateToken(Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final Instant now = Instant.now();

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expirationTime)))
                .signWith(key())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key()).build().parse(token);
            return true;
        } catch (JwtException e) {
            // Logger can be used here
            // logger.error("JWT validation failed: {}", e.getMessage());
        }
        return false;
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
