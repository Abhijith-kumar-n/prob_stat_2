package com.jsonMapper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.jsonMapper.dto.UserRegistrationDto;
import com.jsonMapper.service.UserService;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@Controller
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
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
