package com.jegeap.mapper.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jegeap.dto.UserDTO;
import com.jegeap.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
    public UserDTO toDTO(User user);
    public User toModel(UserDTO userDTO);
	
}
