package se.kth.iv1201.recruitment.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

//TODO: JavaDocs
@Component
public class JwtTokenProvider {
    public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${app.jwtExpiration}")
    private String jwtExpiration;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // TODO: Set expiration in JWT

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getRole())
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        }
    }
}
