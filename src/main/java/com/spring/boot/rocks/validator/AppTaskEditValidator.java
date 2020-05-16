package com.spring.boot.rocks.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.boot.rocks.model.AppTask;

@Component

public class AppTaskEditValidator implements Validator {
	
	
	@Override
	public boolean supports(Class<?> aClass) {
		return AppTask.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		AppTask task = (AppTask) o;

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskname", "common.message");
//		if (task.getUsername().length() < 6 || task.getUsername().length() > 32) {
//			errors.rejectValue("taskname", "common.message");
//		}
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskname", "not.empty.taskname");

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskdescription", "not.empty.taskdescription");
//		if (task.getPassword().length() < 8 || task.getPassword().length() > 32) {
//			errors.rejectValue("password", "Size.taskForm.password");
//		}

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "users", "not.empty.multi.users");

		
	}
}
