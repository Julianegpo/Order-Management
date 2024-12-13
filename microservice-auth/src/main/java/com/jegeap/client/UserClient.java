package com.jegeap.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jegeap.dto.UserDTO;

@FeignClient(name="service-users")
public interface UserClient {

	@GetMapping("/{username}")
	public UserDTO findByUsername(String username);
	@PostMapping("/create")
	public UserDTO createUser(@RequestBody UserDTO userDTO);
	
}
