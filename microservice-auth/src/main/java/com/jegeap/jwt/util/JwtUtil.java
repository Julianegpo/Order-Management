package com.jegeap.jwt.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jegeap.dto.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    private static final String SECRET_KEY = "my-secret-key";
    private static final long EXPIRATION_TIME = 86400000; // 1 día

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
}
