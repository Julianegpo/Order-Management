package com.jegeap.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jegeap.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtUtil {

    private static final String SECRET_KEY = "qwerty123456";
    private static final long EXPIRATION_TIME = 60000; // 1 minuto

    public static String generateToken(UserDTO user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Token must start with 'Bearer '");
            }
            String jwtToken = token.substring("Bearer ".length());

            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtToken)
                    .getBody();

            if (claims.getExpiration().before(new Date())) {
                throw new ExpiredJwtException(null, claims, "Token expired");
            }
            return true; // valid token
        } catch (ExpiredJwtException e) {
            System.err.println("Token expired: " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid token: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Token validation failed: " + e.getMessage());
        }
        return false; // invalid token
    }

}
