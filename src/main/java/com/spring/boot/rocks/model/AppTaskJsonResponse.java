package com.spring.boot.rocks.model;

import java.util.Map;

public class AppTaskJsonResponse {
	
	private AppTask apptask;
	private boolean validated;
	private Map<String, String> errorMessages;
	
	public AppTask getApptask() {
		return apptask;
	}
	public void setApptask(AppTask apptask) {
		this.apptask = apptask;
	}
	
	public boolean isValidated() {
		return validated;
	}
	
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	
}