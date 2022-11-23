package com.esd.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/home")
	public String getMessage() {
		System.out.println("here");
		return "user-home";
		
	}
}
