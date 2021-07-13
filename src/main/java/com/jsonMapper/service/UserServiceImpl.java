package com.jsonMapper.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsonMapper.Repository.UserRepository;
import com.jsonMapper.dto.UserRegistrationDto;
import com.jsonMapper.model.Role;
import com.jsonMapper.model.User;



@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	public Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		logger.info("saving User "+registrationDto.toString());
		User user = new User(registrationDto.getUserName(), 
				registrationDto.getPhoneNo(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}
	public Long findUserByUsername(String username){
		return userRepository.findByUserName(username).getId();
	}

	public Boolean isUserEnabled(String username){
		logger.info("getting user by userId : "+username);
		User user = userRepository.findByUserName(username);
		if(user == null) {
			return false;
		}
		logger.info("valid user");
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles())).isEnabled();

	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("getting user by userId : "+username);
		User user = userRepository.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		logger.info("valid user");
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
