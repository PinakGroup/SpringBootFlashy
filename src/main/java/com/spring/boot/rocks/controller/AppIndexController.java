package com.spring.boot.rocks.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.rocks.model.AppTask;
import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.model.AppUserProfile;
import com.spring.boot.rocks.repository.AppRoleJPARepository;
import com.spring.boot.rocks.repository.AppTaskRepository;
import com.spring.boot.rocks.repository.AppUserDocumentRepository;
import com.spring.boot.rocks.repository.AppUserJPARepository;
import com.spring.boot.rocks.repository.AppUserProfileRepository;
import com.spring.boot.rocks.repository.ChartData;
import com.spring.boot.rocks.service.AppRoleService;
import com.spring.boot.rocks.service.AppUserProfileService;
import com.spring.boot.rocks.service.AppUserService;

@Controller
@RequestMapping("/")
public class AppIndexController {

	GetSystemResourcesDetails gsrd = new GetSystemResourcesDetails();

	@Autowired
	AppUserService appUserService;

	@Autowired
	AppRoleService appRoleService;

	@Autowired
	AppRoleJPARepository appRoleRepository;

	@Autowired
	AppUserJPARepository appUserJPARepository;

	@Autowired
	AppUserDocumentRepository appUserDocumentRepository;

	@Autowired
	AppTaskRepository taskRepo;

	@Autowired
	AppUserProfileService appUserProfileProfileService;

	@Autowired
	AppUserProfileRepository appUserProfileRepository;

	@GetMapping("/")
	public String homePage(Model model, @RequestParam(defaultValue = "0") int page)
			throws JsonProcessingException, ParseException {

		List<AppUser> users = appUserService.findAllUsers();

		ObjectMapper objectMapper = new ObjectMapper();

		List<ChartData> projectData = appUserJPARepository.getUserActiveStatus();
		String jsonString = objectMapper.writeValueAsString(projectData);
// System.out.println(jsonString);

// List<ChartData> findByRoles = appUserJPARepository.findByRoles();
// String jsonString2 = objectMapper.writeValueAsString(findByRoles);
// System.out.println(jsonString2);

// for (AppUser au : users) {
// System.out.println(au.getUsername() + " has " +
// appUserJPARepository.findAllAppuserdocumentset(au.getId()) + "
// Documents.!!");
// }

		List<ChartData> getAppuserDocumentCount = appUserJPARepository.getAppuserDocumentCount();
		String jsonString3 = objectMapper.writeValueAsString(getAppuserDocumentCount);
// System.out.println(jsonString3);

		List<ChartData> getAppuserRoleCount = appUserJPARepository.getAppuserRoleCount();
		String jsonString4 = objectMapper.writeValueAsString(getAppuserRoleCount);
// System.out.println(jsonString4);

// List<AppUser> activeusers = appUserJPARepository.findByUserenabledTrue();
// for (AppUser au : activeusers) {
// System.out.println(au.getUsername() + " is active");
// }

// List<AppUser> inactiveusers = appUserJPARepository.findByUserenabledFalse();
// for (AppUser au : inactiveusers) {
// System.out.println(au.getUsername() + " is In-Active");
// }
// DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd yyyy");
		LocalDateTime now = LocalDateTime.now();
// Date serverDate = Calendar.getInstance().getTime();
// System.out.println(dtf.format(now));

		long allusers = appUserService.findAllUsers().size();
		long activeusers = appUserJPARepository.findByUserenabledTrue().size();
		long inactiveusers = appUserJPARepository.findByUserenabledFalse().size();

		AppUser currentUserId = appUserJPARepository.getAppuserIdByUsername(getPrincipal());
		model.addAttribute("currentUserId", appUserProfileRepository.findProfileIdByAppUser(currentUserId));

		model.addAttribute("users", users);
		model.addAttribute("totaUsers", appUserService.findAllUsers().size());
		model.addAttribute("activeUserPercentage", percent(activeusers, allusers));
		model.addAttribute("inactiveUserPercentage", percent(inactiveusers, allusers));
		model.addAttribute("activeusers", activeusers);
		model.addAttribute("inactiveusers", inactiveusers);
		model.addAttribute("totalDocuments", appUserDocumentRepository.findAll().size());

		model.addAttribute("serverDate", dtf.format(now));
		model.addAttribute("projectStatusCnt", jsonString);
		model.addAttribute("barchartdata", jsonString);
		model.addAttribute("barchartdata2", jsonString3);
		model.addAttribute("barchartdata3", jsonString4);
		model.addAttribute("metaTitle", "Home");

		model.addAttribute("oepratingSystem", "OS: " + gsrd.getOSname());
		model.addAttribute("oepratingSystemVer", gsrd.getOSVersion());
		model.addAttribute("percentDiskSpaceAvailable",
				percentdouble(gsrd.getUsableDiskSpaceInGb(), gsrd.getTotalDiskSpaceInGb()));
		model.addAttribute("totalDiskSpace", round(gsrd.getTotalDiskSpaceInGb(), 1));

		List<AppTask> tasks = taskRepo.findAll();
		model.addAttribute("tasks", tasks);
		model.addAttribute("totaltasks", taskRepo.findAll().size());

		model.addAttribute("data", taskRepo.findAll(PageRequest.of(page, 4, Sort.by(Order.asc("id")))));
		model.addAttribute("currentPage", page);

		List<AppUser> top7Uesrs = appUserJPARepository.findTop3ByUsernamenoLessThanOrderByIdAsc();
//int i=1;
// for (AppUser au : top10Uesrs) {
// System.out.println(au.getUsername() + " number " + i);
// }
		model.addAttribute("top7Uesrs", top7Uesrs);

		model.addAttribute("systemStats", gsrd.getSystemStats());

//System.out.println("\n\n\nHere you Go.... \n\n\n"+ gsrd.getSystemStats());

		return "index";
	}

	static long percent(long a, long b) {
		float result = 0;
		result = (a * 100) / b;
		return (int) result;
	}

	static long percentdouble(double a, double b) {
		double result = 0;
		result = (a * 100) / b;
		return (int) result;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@GetMapping("/index")
	public String indexPage() {
		return "redirect:/";
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

}
