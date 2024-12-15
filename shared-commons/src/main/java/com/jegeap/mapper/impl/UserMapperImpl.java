package com.jegeap.mapper.impl;

import com.jegeap.dto.UserDTO;
import com.jegeap.mapper.interfaces.UserMapper;
import com.jegeap.model.User;

public class UserMapperImpl implements UserMapper {

	@Override
	public UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
	}

	@Override
	public User toModel(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
	}

}
