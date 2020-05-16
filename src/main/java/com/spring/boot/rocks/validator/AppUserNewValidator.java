package com.spring.boot.rocks.validator;

import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.service.AppUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AppUserNewValidator implements Validator {
	@Autowired
	private AppUserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return AppUser.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		AppUser user = (AppUser) o;

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "common.message");
		if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "common.message");
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "duplicate.username.error");
		}
		
		if (userService.findByUseremail(user.getUseremail()) != null) {
			errors.rejectValue("useremail", "duplicate.email.error");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "useremail", "common.message");

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
//			errors.rejectValue("password", "Size.userForm.password");
//		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "password.passwordConfirm.mismatch");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "common.message");

		
	}
}
