package com.jsonMapper.Controller;

import com.jsonMapper.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*",allowedHeaders="*")
@Controller
@RequestMapping("/users")
public class MainController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/*@PostMapping("/login")
	public loginUser(@RequestBody UserLoginDto userLoginDto){
		System.out.println(userLoginDto.toString());
		return "login";
	}*/
}
