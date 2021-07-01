package com.jsonMapper.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jsonMapper.dto.UserRegistrationDto;
import com.jsonMapper.model.User;



public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
