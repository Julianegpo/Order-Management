package com.jegeap.service;

import com.jegeap.dto.UserDTO;

public interface UserService {
	public UserDTO findByUsername(String username) throws Exception;
	public UserDTO createUser(UserDTO userDTO) throws Exception;
}
