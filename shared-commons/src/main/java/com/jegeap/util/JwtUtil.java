package com.jegeap.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jegeap.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    private static final String SECRET_KEY = "qwerty123456";
    // private static final long EXPIRATION_TIME = 86400000; // 1 día
    private static final long EXPIRATION_TIME = 60000; // 1 minuto

    public static String generateToken(UserDTO user) {
        /* Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail()); */
        return Jwts.builder()
                // .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            String jwtToken = token.substring("Bearer ".length());
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
                return false;
            }
            return Jwts.parser().isSigned(jwtToken);
        } catch (Exception e) {
            return false;
        }
    }

}
