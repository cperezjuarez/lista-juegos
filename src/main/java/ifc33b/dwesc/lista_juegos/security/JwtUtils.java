package ifc33b.dwesc.lista_juegos.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    // Generar JWT
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    // Obtener un usuario del token
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Validar token
    public Boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .build()
                .parseSignedClaims(authToken);
            return true;

        } catch (SecurityException e) {
            System.err.println("Invalid JWT signature: {}" + e);
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: {}" + e);
        } catch (ExpiredJwtException e) {
            System.err.println("Expired JWT token: {}" + e);
        } catch (UnsupportedJwtException e) {
            System.err.println("Unsupported JWT token: {}" + e);
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: {}" + e);
        }

        return false;
    }

    // Obtener clave para firmar los tokens
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
