package com.jegeap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jegeap.dto.UserDTO;
import com.jegeap.model.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
    UserDTO toDTO(User user);
    User toModel(UserDTO userDTO);
	
}
