package com.jegeap.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jegeap.dto.UserDTO;
import com.jegeap.mapper.UserMapper;
import com.jegeap.model.User;
import com.jegeap.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	private UserMapper userMapper;
	
	@Override
	@CircuitBreaker(name = "users", fallbackMethod = "fallbackFindByUsername")
	public UserDTO findByUsername(String username) throws Exception {
		return userMapper.toDTO(repository.findOneByUsername(username));
	}

	@Override
	@CircuitBreaker(name = "users", fallbackMethod = "fallbackCreateUser")
	public UserDTO createUser(UserDTO userDTO) throws Exception {
		return userMapper.toDTO(repository.save(userMapper.toModel(userDTO)));
	}

	private UserDTO fallbackFindByUsername(String username, Throwable ex){
		System.out.println("fallback method reached by ->" + ex);
		return new UserDTO();
	}

	private UserDTO fallbackCreateUser(UserDTO userDTO, Throwable ex){
		System.out.println("fallback method reached by ->" + ex);
		return new UserDTO();
	}
	
}
