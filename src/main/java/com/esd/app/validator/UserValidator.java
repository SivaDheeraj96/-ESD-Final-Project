package com.esd.app.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.esd.app.dao.UserDAO;
import com.esd.app.exception.UserException;
import com.esd.app.pojo.User;

@Component
public class UserValidator implements Validator{

	@Autowired
	private UserDAO userDAO;
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","email-empty", "First Name can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName","email-empty", "Last Name can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","email-empty", "Email can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","password-empty", "Password can't be empty");
		User user = (User) target;
		String email = user.getEmail();
		if(!email.isEmpty()) {
			System.out.println("inside validation");
			Pattern p = Pattern.compile("^(.+)@(\\S+)$");
			Matcher m = p.matcher(email);
			if(!m.find()) {
				System.err.println("not found");
				errors.rejectValue("email", "email-valid", null, "Email is not valid");
			}
		}
	}
	
	public void validateLogin(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","email-empty", "Email can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","password-empty", "Password can't be empty");
		User user = (User) target;
		String email = user.getEmail();
		if(!email.isEmpty()) {
			System.out.println("inside validation");
			Pattern p = Pattern.compile("^(.+)@(\\S+)$");
			Matcher m = p.matcher(email);
			if(!m.find()) {
				System.err.println("not found");
				errors.rejectValue("email", "email-valid", null, "Email is not valid");
			}
		}
	}

}
