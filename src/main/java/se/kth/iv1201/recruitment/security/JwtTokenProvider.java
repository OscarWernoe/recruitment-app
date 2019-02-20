package se.kth.iv1201.recruitment.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpiration}")
    private String jwtExpiration;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        byte[] keyBytes = jwtSecret.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        // TODO: Set expiration in JWT

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getRole())
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
