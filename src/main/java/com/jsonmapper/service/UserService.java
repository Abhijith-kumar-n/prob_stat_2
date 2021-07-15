package com.jsonmapper.service;

import com.jsonmapper.dto.UserRegistrationDto;
import com.jsonmapper.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

    Long findUserByUsername(String username);

    Boolean isUserEnabled(String userName);
}
