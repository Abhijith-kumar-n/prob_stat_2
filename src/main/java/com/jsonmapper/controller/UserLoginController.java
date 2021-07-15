package com.jsonmapper.controller;

import com.jsonmapper.dto.UserLoginDto;
import com.jsonmapper.service.UserService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping(value="/users")
public class UserLoginController {

	@Autowired
	private UserService userService;

	static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
	@GetMapping("/findByUserName/{username}")
	public Long findUserByName(@PathVariable String username){
		return userService.findUserByUsername(username);
	}

	@PostMapping("/login")
	public JSONObject loginUser(@RequestBody UserLoginDto userLoginDto){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("authenticated",userService.isUserEnabled(userLoginDto.getUserName()));
		try {
			jsonObject.put("userId", findUserByName(userLoginDto.getUserName()));
		}
		catch (NullPointerException nullPointerException){
			jsonObject.put("userId",null);
			logger.debug("invalid");
		}
		return jsonObject;
	}


}
