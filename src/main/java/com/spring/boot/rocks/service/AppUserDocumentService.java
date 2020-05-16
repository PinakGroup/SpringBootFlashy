package com.spring.boot.rocks.service;

import java.util.List;

import com.spring.boot.rocks.model.AppUserDocument;

public interface AppUserDocumentService {

	AppUserDocument findById(long id);

	List<AppUserDocument> findAll();

	List<AppUserDocument> findAllByUserId(Long userid);
	
	void saveDocument(AppUserDocument document);

	void deleteById(long id);
}
