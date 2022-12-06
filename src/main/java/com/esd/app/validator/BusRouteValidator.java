package com.esd.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.esd.app.pojo.BusRoute;

@Component
public class BusRouteValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return BusRoute.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sourceName", "source-empty","Source Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destinationName", "destination-empty","Destination Can't be empty");
	}

}
