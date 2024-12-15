package com.jegeap.jwt.util;

import java.util.Date;

import com.jegeap.dto.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    private static final String SECRET_KEY = "my-secret-key";
    private static final long EXPIRATION_TIME = 86400000; // 1 día

    public static String generateToken(UserDTO user) {
        return Jwts.builder()
                .setSubject(user.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
