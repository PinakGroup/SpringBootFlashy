package com.spring.boot.rocks.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppRoleJPARepository;
import com.spring.boot.rocks.repository.AppUserJPARepository;
import com.spring.boot.rocks.service.AppRoleService;
import com.spring.boot.rocks.service.AppUserService;
import com.spring.boot.rocks.service.EmailService;
import com.spring.boot.rocks.validator.AppUserEditValidator;
import com.spring.boot.rocks.validator.AppUserNewValidator;

@Controller
@RequestMapping("/")
public class AppUserController {

	@Autowired
	AppUserService appUserService;

	@Autowired
	AppRoleService appRoleService;

	@Autowired
	AppUserJPARepository appUserJPARepository;

	@Autowired
	AppRoleJPARepository appRoleRepository;

	@Autowired
	AppUserNewValidator appUserNewValidator;

	@Autowired
	AppUserEditValidator appUserEditValidator;

	@Autowired
	EmailService emailService;

	@Value("${server.port}")
	private int serverPort;

	@RequestMapping(value = { "tableusers" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		String tableusers = "tableusers";
		model.addAttribute("tableusers", tableusers);

		List<AppUser> users = appUserService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("metaTitle", "All Users");
		return "tableusers";
	}

	@RequestMapping(value = { "tableusersactive" }, method = RequestMethod.GET)
	public String listActiveUsers(ModelMap model) {

		String tableusersactive = "tableusersactive";
		model.addAttribute("tableusersactive", tableusersactive);

		List<AppUser> users = appUserJPARepository.findByUserenabledTrue();
		model.addAttribute("users", users);
		return "tableusers";
	}

	@RequestMapping(value = { "tableusersinactive" }, method = RequestMethod.GET)
	public String listInactiveUsers(ModelMap model) {

		String tableusersinactive = "tableusersinactive";
		model.addAttribute("tableusersinactive", tableusersinactive);

		List<AppUser> users = appUserJPARepository.findByUserenabledFalse();
		model.addAttribute("users", users);
		return "tableusers";
	}

	@RequestMapping(value = { "usersearch" }, method = RequestMethod.GET)
	public String findByUsernameLike(@RequestParam("userNameCS") String username, ModelMap model) {
		List<AppUser> users = appUserJPARepository.findByUsernameLike(username);
		model.addAttribute("users", users);
		return "tableusers";
	}

	@RequestMapping(value = { "usersearchignorecase" }, method = RequestMethod.GET)
	public String findByUsernameIgnoreCase(@RequestParam("userNameCI") String username, ModelMap model) {
		List<AppUser> users = appUserJPARepository.findByUsernameIgnoreCaseContaining(username);
		model.addAttribute("users", users);
		return "tableusers";
	}

	@RequestMapping(value = { "userlistbycreatedateage" }, method = RequestMethod.GET)
	public String listUsersBycreatedateage(ModelMap model) {
		List<AppUser> users = appUserService.findAllUsers();
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int currentyear = localDate.getYear();
//		int month = localDate.getMonthValue();
//		int day   = localDate.getDayOfMonth();
//		System.out.println("Current Year is - "+currentyear);
		HashMap<String, Integer> userlistmap = new HashMap<>();
		for (AppUser u : users) {
			userlistmap.put(u.getUserfirstname() + " " + u.getUserlastname(),
					currentyear - getYear(u.getUserdatecreated()));
		}
		model.addAttribute("users", userlistmap);
		return "userlistbyage";
	}
	
	@GetMapping("/tableusersREST")
	public String userindexPage() {
		return "tableusersREST";
	}

	@RequestMapping(value = { "/userEdit", "/userEdit/{id}" }, method = RequestMethod.GET)
	public String userRegistration(Model model, @PathVariable(required = false, name = "id") Long id) {

		if (null != id) {
			String editinguser = "editinguser";
			model.addAttribute("editinguser", editinguser);
			model.addAttribute("appuser", appUserService.findById(id));
			model.addAttribute("appuserroles", appRoleRepository.findAll());
		} else {
			String creatinguser = "creatinguser";
			model.addAttribute("creatinguser", creatinguser);
			model.addAttribute("appuserroles", appRoleRepository.findAll());
			model.addAttribute("appuser", new AppUser());
		}
		return "registration";
	}

	@RequestMapping(value = "/userEdit", method = RequestMethod.POST)
	public String userRegistration(@ModelAttribute("appuser") @Valid AppUser appuser, BindingResult bindingResult,
			HttpServletRequest request, Model model) {

		String appUrl = request.getScheme() + "://" + request.getServerName();

		model.addAttribute("appuserroles", appRoleRepository.findAll());

		String somemsg = getPrincipal();
		model.addAttribute("somemsg", somemsg);

		if (null != appuser.getId()) {
			appUserEditValidator.validate(appuser, bindingResult);
			model.addAttribute("appuser", appuser);

			String editinguser = "editinguser";
			model.addAttribute("editinguser", editinguser);
		} else {
			appUserNewValidator.validate(appuser, bindingResult);
			model.addAttribute("appuser", appuser);
			String creatinguser = "creatinguser";
			model.addAttribute("creatinguser", creatinguser);
		}

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		if (null != appuser.getId()) {
			// System.out.println("Update in progress....");
			appUserService.updateAppUser(appuser);
		} else {
			// System.out.println("Create New in progress....");
			appUserService.saveAppUser(appuser);
			sendRegistrationConfirmationEmail(appuser, appUrl);
		}

		return "redirect:/tableusers";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {

		AppUser user = appUserService.findByUserconfirmationtoken(token);

		if (user == null) {
			modelAndView.addObject("invalidToken", "Error.!!  This is an invalid activation link.");
		} else {
			modelAndView.addObject("confirmationToken", user.getUserconfirmationtoken());
		}

		modelAndView.setViewName("confirm");
		return modelAndView;
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult,
			@RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

		modelAndView.setViewName("confirm");
		AppUser user = appUserService.findByUserconfirmationtoken(requestParams.get("token"));
		user.setUserenabled(true);
//		appUserService.updateAppUserActiveStatus(user);
		System.out.println("Now User " + user.getUsername() + " is enabled? - " + user.isUserenabled());
		modelAndView.addObject("successMessage", "Your account is enabled!");
		modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}

	@RequestMapping(value = "/userDelete/{id}", method = RequestMethod.GET)
	public String notesDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		appUserService.deleteAppUser(id);
//			model.addAttribute("listAppUser", appUserService.findAll());
		return "redirect:/tableusers";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AppUser deleteAppUser(@PathVariable Long id) {
        appUserJPARepository.deleteById(id);
		return null;
    }
	
	@GetMapping("/userfindOne") 
	@ResponseBody
	public Optional<AppUser> findOne (Long id) {
		return appUserJPARepository.findById(id);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout, String userinactive) {
		if (error != null)
//			model.addAttribute("error", "Your username and password is invalid.");
			if (logout != null)
//			model.addAttribute("message", "You have been logged out successfully.");
				if (userinactive != null)
					model.addAttribute("message", "You account is not yet active.");

		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);

			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:login";
	}

	@RequestMapping(value = "Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "error-403";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public String getTimeStamp() {
		TimeZone mytimeZone = TimeZone.getTimeZone("EST");
		Calendar calendar = Calendar.getInstance(mytimeZone);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		simpleDateFormat.setTimeZone(mytimeZone);
		String setTimeStamp = simpleDateFormat.format(calendar.getTime());
		return setTimeStamp;
	}

	@SuppressWarnings("unused")
	private int getAge(String dobString) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse(dobString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date == null)
			return 0;
		Calendar dob = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		dob.setTime(date);
		int year = dob.get(Calendar.YEAR);
		int month = dob.get(Calendar.MONTH);
		int day = dob.get(Calendar.DAY_OF_MONTH);
		dob.set(year, month + 1, day);
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		return age;
	}

	public int getYear(Date date) {
		// Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		// int month = localDate.getMonthValue();
		// int day = localDate.getDayOfMonth();
		// System.out.println("Current Year is - " + year);
		return year;
	}

	public void sendRegistrationConfirmationEmail(AppUser appUser, String appURL) {
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(appUser.getUseremail());
		registrationEmail.setSubject("Registration Confirmation");
		registrationEmail.setText("To confirm your e-mail address, please click the link below:\n" + appURL + ":"
				+ serverPort + "/confirm?token=" + appUser.getUserconfirmationtoken());
		registrationEmail.setFrom("noreply@domain.com");
		System.err.println(
				"\n\n\n#################################################################################################################");
		System.err.println("Confirmation URL for " + appUser.getUsername() + " is " + appURL + ":8080/confirm?token="
				+ appUser.getUserconfirmationtoken());
		System.err.println(
				"#################################################################################################################\n\n\n");
		emailService.sendEmail(registrationEmail);
	}

}
