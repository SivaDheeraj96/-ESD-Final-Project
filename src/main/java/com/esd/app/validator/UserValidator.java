package com.esd.app.validator;

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","email-empty", "Email can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","password-empty", "Password can't be empty");
	}

}
