package com.jsonMapper.service;

import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.jsonMapper.dto.UserRegistrationDto;
import com.jsonMapper.model.User;



public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

    Long findUserByUsername(String username);

    Boolean isUserEnabled(String userName);
}
