package com.jsonMapper.Controller;

import com.jsonMapper.dto.UserLoginDto;
import com.jsonMapper.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.InvocationTargetException;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping(value="/users")
public class MainController {

	@Autowired
	private UserService userService;

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
			System.out.println("invalid");
		}
		return jsonObject;
	}


}
