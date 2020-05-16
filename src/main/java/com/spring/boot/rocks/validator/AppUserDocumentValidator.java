package com.spring.boot.rocks.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.boot.rocks.model.AppUserDocumentBucket;

@Component
public class AppUserDocumentValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return AppUserDocumentBucket.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		AppUserDocumentBucket file = (AppUserDocumentBucket) obj;

		if (file.getFile() != null || file.getFile().isEmpty()) {
			if (file.getFile().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
			errors.rejectValue("file", "missing.file");
		}
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "missing.file");
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "appocumenttype", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "appocumenttype", "NoAppDocumenttypeSelected");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NoAppDocumenttypeSelected");
		
		
	}
}
