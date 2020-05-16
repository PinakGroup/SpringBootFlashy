package com.spring.boot.rocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.rocks.model.AppUserDocument;
import com.spring.boot.rocks.repository.AppUserDocumentRepository;

@Service("userDocumentService")
@Transactional
public class AppUserDocumentServiceImpl implements AppUserDocumentService {

	@Autowired
	AppUserDocumentRepository appuserdocumentRepo;
	

	public AppUserDocument findById(long id) {
		return appuserdocumentRepo.findById(id).get();
	}

	public List<AppUserDocument> findAll() {
		return appuserdocumentRepo.findAll();
	}

	public List<AppUserDocument> findAllByUserId(Long userId) {
		return appuserdocumentRepo.findByUserid(userId);
	}

	public void saveDocument(AppUserDocument document) {
				appuserdocumentRepo.save(document);
	}

	public void deleteById(long id) {
		appuserdocumentRepo.deleteById(id);
	}

}
