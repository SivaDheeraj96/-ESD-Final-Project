package com.esd.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.esd.app.dao.UserDAO;
import com.esd.app.exception.UserException;
import com.esd.app.pojo.User;
import com.esd.app.validator.UserValidator;

@Controller
public class LoginController {
	@Autowired
	private UserValidator validator;
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/login")
	public String showLogin(Model model, User user) {
		return "login-view";
	}
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute User user, BindingResult result, SessionStatus status, Model model) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "login-view";
		}
		User savedUser;
		try {
			savedUser = userDAO.get(user.getEmail());
			if(savedUser == null || !savedUser.getPassword().equals(user.getPassword())) {
				System.out.println("authentication failed");
				model.addAttribute("user_not_present", "Email not found! Please register using signup");
				return "login-view";
			}
		} catch (UserException e) {
			e.printStackTrace();
			return "login-view";
		}
		status.setComplete();
		model.addAttribute("user", savedUser );
		System.out.println("authentication success!!!");
		return "user-home";
	}
}
