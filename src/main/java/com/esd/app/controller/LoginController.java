package com.esd.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String handleLogin(@ModelAttribute User user, BindingResult result, SessionStatus status, Model model, HttpServletRequest req) {
		validator.validateLogin(user, result);
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
		HttpSession session = req.getSession();
		session.setAttribute("user", savedUser);
		status.setComplete();
		model.addAttribute("user", savedUser );
		System.out.println("authentication success!!!");
		return savedUser.getRole() == 0?"user-home":"admin/admin-home";
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.removeAttribute("user");
		session.invalidate();
		System.out.println("invalidating session:"+session.getId());
		return "login-view";
	}
}
