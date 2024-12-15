package com.jegeap.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jegeap.client.UserClient;
import com.jegeap.dto.UserDTO;
import com.jegeap.jwt.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private UserClient userClient;
	
    @PostMapping("/token")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
    	 try {
             // Obtener el usuario desde el microservicio de usuarios
             UserDTO user = userClient.findByUsername(userDTO.getUsername());

             // Validar la contraseña
             if (!user.getPassword().equals(user.getPassword())) {
                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
             }

             // Generar el token JWT
             String token = JwtUtil.generateToken(user);

             // Retornar el token
             return ResponseEntity.ok("Bearer " + token);
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
         }
    }
    
}
