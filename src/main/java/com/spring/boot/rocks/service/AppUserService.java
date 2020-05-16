package com.spring.boot.rocks.service;

import java.util.List;
import java.util.Optional;

import com.spring.boot.rocks.model.AppUser;

public interface AppUserService {

    List<AppUser> findAllUsers();

    Optional<AppUser> findById(Long id);

    AppUser saveAppUser(AppUser appUser);
    
    AppUser updateAppUser(AppUser appUser);

    void deleteAppUser(Long id);

	AppUser findByUseremail(String useremail);

	AppUser findByUserconfirmationtoken(String userconfirmationtoken);

	AppUser  findByUsername(String username);
	
	public Optional<AppUser> delete(Long id);
	
//	AppUser updateAppUserActiveStatus (AppUser appUser);
	
//	AppUser appUserActivation (AppUser appUser);

//	AppUser appUserNewWithActivation(AppUser appUser);
	
//	AppUser findById(long id);

}
