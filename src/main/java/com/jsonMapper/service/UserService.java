package com.jsonMapper.service;

import com.jsonMapper.dto.UserRegistrationDto;
import com.jsonMapper.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

    Long findUserByUsername(String username);

    Boolean isUserEnabled(String userName);
}
