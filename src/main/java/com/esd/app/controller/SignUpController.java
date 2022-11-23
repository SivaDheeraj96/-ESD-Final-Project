package com.esd.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.esd.app.dao.UserDAO;
import com.esd.app.exception.UserException;
import com.esd.app.pojo.User;
import com.esd.app.validator.UserValidator;

@Controller
public class SignUpController {
	@Autowired
	private UserValidator validator;
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/signup")
	public String showSignUp() {
		return "sign-up-view";
	}
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute User user, BindingResult result, SessionStatus status, Model model) {
		validator.validate(user, result);
		
		if(result.hasErrors()) {
			return "sign-up-view";
		}
		try {
			User savedUser = userDAO.get(user.getEmail());
			if(savedUser != null && savedUser.getEmail().equals(user.getEmail())) {
				model.addAttribute("user_already_present", "Email is already registered! Please using sign In link to Sign In");
				return "login-view";
			}
		} catch (UserException e) {
			e.printStackTrace();
			return "login-view";
		}
		try {
			userDAO.create(user);
		} catch (UserException e) {
			e.printStackTrace();
			model.addAttribute("error", "Error while Registering! Please try again");
			return "sign-up-view";			
		}
		model.addAttribute("user_registered", "Registered Successfully !!! Please Sign In");
		status.setComplete();
		return "login-view";
	}
}
