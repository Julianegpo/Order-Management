package com.jegeap.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jegeap.client.UserClient;
import com.jegeap.dto.UserDTO;

import io.jsonwebtoken.Jwts;

@Service
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserClient feignUsers;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public AuthService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String authenticate(String username, String password) {
        UserDTO user = feignUsers.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return generateToken(user);
    }

    private String generateToken(UserDTO user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", "ROLE_USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .compact();
    }
}

