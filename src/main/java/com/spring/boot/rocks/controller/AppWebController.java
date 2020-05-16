package com.spring.boot.rocks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppWebController {

	@GetMapping("/contactus")
	public String contactPage() {
		return "static_contactus";
	}
 
	@GetMapping("/aboutus")
	public String aboutPage() {
		return "static_aboutus";
	}

	@GetMapping("/contacts")
	public String contactsPage() {
		return "contacts";
	}

	@GetMapping("/ecommerce")
	public String ecommercePage() {
		return "ecommerce";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	@GetMapping("/passwordforgot")
	public String forgotpasswordPage() {
		return "passwordforgot";
	}

	@GetMapping("/passwordreset")
	public String passwordresetPage() {
		return "passwordreset";
	}

	@GetMapping("/formvalidation")
	public String formvalidationPage() {
		return "formvalidation";
	}

	@GetMapping("/formgeneral")
	public String formgeneralPage() {
		return "formgeneral";
	}

	@GetMapping("/tablesimple")
	public String tablesimplePage() {
		return "tablesimple";
	}
	

}
