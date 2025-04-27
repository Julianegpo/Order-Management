package com.jegeap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jegeap.dto.UserDTO;
import com.jegeap.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/{username}")
	public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
		UserDTO userDTO;
		try {
			userDTO = service.findByUsername(username);
			return new ResponseEntity<UserDTO>(userDTO,
					userDTO != null
							? HttpStatus.OK
							: HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/create")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		if (userDTO == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				return service.createUser(userDTO) != null 
						? new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED)
						: new ResponseEntity<UserDTO>(userDTO, HttpStatus.CONFLICT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
	}

}
