package com.spring.boot.rocks.model;

import org.springframework.web.multipart.MultipartFile;

public class AppUserDocumentBucket {

	MultipartFile file;
	
	String appuserdocumentname;

	String appuserdocumentdescription;

	String appuserdocumenttype;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getAppuserdocumentname() {
		return appuserdocumentname;
	}

	public void setAppuserdocumentname(String appuserdocumentname) {
		this.appuserdocumentname = appuserdocumentname;
	}

	public String getAppuserdocumentdescription() {
		return appuserdocumentdescription;
	}

	public void setAppuserdocumentdescription(String appuserdocumentdescription) {
		this.appuserdocumentdescription = appuserdocumentdescription;
	}

	public String getAppuserdocumenttype() {
		return appuserdocumenttype;
	}

	public void setAppuserdocumenttype(String appuserdocumenttype) {
		this.appuserdocumenttype = appuserdocumenttype;
	}

	

}