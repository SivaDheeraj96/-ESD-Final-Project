package com.esd.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.esd.app.pojo.BusRoute;
import com.esd.app.pojo.BusTrip;

@Component
public class BusTripValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return BusTrip.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tripDate", "tripDate-empty","Date Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "busRoute", "busRoute-empty","busRoute Can't be empty");
	}

}
