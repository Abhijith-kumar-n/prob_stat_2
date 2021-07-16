package com.jsonmapper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.jsonmapper.dto.UserRegistrationDto;
import com.jsonmapper.service.UserService;

import java.sql.SQLIntegrityConstraintViolationException;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
//	@GetMapping
//	public String showRegistrationForm() {
//		return "registration";
//	}
	
	@PostMapping
	public String registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
		try {
			userService.save(registrationDto);
			return "Registered Successfully";
		}
		catch (Exception exception){
			return "User Name "+registrationDto.getUserName()+" Already in use ";
		}
	}
}
