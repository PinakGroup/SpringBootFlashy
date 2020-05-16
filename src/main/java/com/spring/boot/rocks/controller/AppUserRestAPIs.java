package com.spring.boot.rocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppUserJPARepository;

@RestController
@RequestMapping("/api/AppUser")
public class AppUserRestAPIs {

	@Autowired
	AppUserJPARepository appUserJPARepository;
	
	@JsonIgnore
	@GetMapping(value = "/all", produces = "application/json")
	public List<AppUser> getResource() {
		return appUserJPARepository.findAll();
//		return appUserJPARepository.getOnlyFourFields();
	}

	@DeleteMapping(value = "/delete/{id}")
	public String deleteAppUser(@PathVariable Long id) {
		System.out.println("Deleting User :: " + id);
		appUserJPARepository.deleteById(id);
		return "OK!";
	}
	

	@PostMapping(value = "/deactivateUser/{id}")
	public String deactivateUser(@PathVariable Long id) {
		System.out.println("De-Activating User :: " + id);
		appUserJPARepository.setAppUserAsInActiveById(id);
		return "OK!";
	}

	@PostMapping(value = "/activateUser/{id}")
	public String activateUser(@PathVariable Long id) {
		System.out.println("Activating User :: " + id);
		appUserJPARepository.setAppUserAsActiveById(id);
		return "OK!";
	}

}