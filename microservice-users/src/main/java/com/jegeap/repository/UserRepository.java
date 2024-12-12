package com.jegeap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jegeap.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	public User findOneByUsername(String username);
}
