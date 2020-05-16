package com.spring.boot.rocks.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.boot.rocks.model.AppUser;

@Component

public class AppUserEditValidator implements Validator {
	
	
	@Override
	public boolean supports(Class<?> aClass) {
		return AppUser.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		AppUser user = (AppUser) o;

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "common.message");
//		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
//			errors.rejectValue("username", "common.message");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "useremail", "common.message");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "common.message");
//		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
//			errors.rejectValue("password", "Size.userForm.password");
//		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "password.passwordConfirm.mismatch");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "common.message");

		
	}
}
