package com.spring.boot.rocks.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.rocks.model.AppTask;
import com.spring.boot.rocks.model.AppTaskJsonResponse;
import com.spring.boot.rocks.repository.AppTaskRepository;
import com.spring.boot.rocks.repository.AppUserJPARepository;
import com.spring.boot.rocks.service.AppTaskService;
import com.spring.boot.rocks.validator.AppTaskEditValidator;
import com.spring.boot.rocks.validator.AppTaskNewValidator;


@Controller
//@RequestMapping("/")
public class AppTaskController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
	}
	
	
	@Autowired
	AppTaskRepository taskRepo;
	
	@Autowired
	AppTaskService appTaskService;
	
	@Autowired
	AppTaskNewValidator appTaskNewValidator;
	
	@Autowired
	AppTaskEditValidator appTaskEditValidator;
	
	@Autowired
	AppUserJPARepository appUserJPARepository;
	
	@PostMapping(value = "/newtask" , produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public AppTaskJsonResponse appTask (@Valid  @ModelAttribute ("appTask") AppTask appTask, BindingResult result) {

		AppTaskJsonResponse response = new AppTaskJsonResponse();

		if (result.hasErrors()) {
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

			response.setValidated(false);
			response.setErrorMessages(errors);
		} else {
			response.setValidated(true);
			response.setApptask(appTask);
			
			appTask.setTaskdatecreated(new Date());
			appTask.setTaskdatemodified(new Date());
			
			
			appTaskService.saveAppTask(appTask);
		}
		return response;
	}
	
	@PostMapping("/updateTaskStatusComplete/{id}")
	public ResponseEntity<Void> updateTaskStatusComplete(@RequestBody Long id) {
		appTaskService.updateTaskStatusComplete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/updateTaskStatusInComplete/{id}")
	public ResponseEntity<Void> updateTaskStatusInComplete(@RequestBody Long id) {
		appTaskService.updateTaskStatusInComplete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/taskEdit", "/taskEdit/{id}" }, method = RequestMethod.GET)
	public String taskRegistration(Model model, @PathVariable(required = false, name = "id") Long id) {

		if (null != id) {
			String editingtask = "editingtask";
			model.addAttribute("editingtask", editingtask);
			model.addAttribute("apptask", appTaskService.findById(id));
			model.addAttribute("apptaskusers", appUserJPARepository.findAll());
		} else {
			String creatingtask = "creatingtask";
			model.addAttribute("creatingtask", creatingtask);
			model.addAttribute("apptaskusers", appUserJPARepository.findAll());
			model.addAttribute("apptask", new AppTask());
		}
		return "tasknew";
	}

	@RequestMapping(value = "/taskEdit", method = RequestMethod.POST)
	public String taskRegistration(@ModelAttribute("apptask") @Valid AppTask apptask, BindingResult bindingResult,
			HttpServletRequest request, Model model) {

		model.addAttribute("apptaskusers", appUserJPARepository.findAll());

		String somemsg = getPrincipal();
		model.addAttribute("somemsg", somemsg);

		if (null != apptask.getId()) {
			appTaskEditValidator.validate(apptask, bindingResult);
			model.addAttribute("apptask", apptask);

			String editingtask = "editingtask";
			model.addAttribute("editingtask", editingtask);
		} else {
			appTaskNewValidator.validate(apptask, bindingResult);
			model.addAttribute("apptask", apptask);
			String creatingtask = "creatingtask";
			model.addAttribute("creatingtask", creatingtask);
		}

		if (bindingResult.hasErrors()) {
			return "tasknew";
		}
		

		if (null != apptask.getId()) {
			// System.out.println("Update in progress....");
			appTaskService.updateAppTask(apptask);
		} else {
			// System.out.println("Create New in progress....");
			appTaskService.saveAppTask(apptask);
		}


		return "redirect:/tabletasks";
	}
	
	
	@RequestMapping(value = { "tabletasks" }, method = RequestMethod.GET)
	public String listTasks(ModelMap model) {
		String tabletasks = "tabletasks";
		model.addAttribute("tabletasks", tabletasks);
		
		List<AppTask> tasks = taskRepo.findAll();
		model.addAttribute("tasks", tasks);
		model.addAttribute("metaTitle", "All Tasks");
		return "tabletasks";
	}
	
	@RequestMapping(value = { "tasksearch" }, method = RequestMethod.GET)
	public String findByUsernameLike(@RequestParam("taskNameCS") String taskname, ModelMap model) {
		List<AppTask> tasks = taskRepo.findByTasknameLike(taskname);
		model.addAttribute("tasks", tasks);
		return "tabletasks";
	}

	@RequestMapping(value = { "tasksearchignorecase" }, method = RequestMethod.GET)
	public String findByUsernameIgnoreCase(@RequestParam("taskNameCI") String taskname, ModelMap model) {
		List<AppTask> tasks = taskRepo.findByTasknameIgnoreCaseContaining(taskname);
		model.addAttribute("tasks", tasks);
		return "tabletasks";
	}
	
	
	
	@RequestMapping(value = { "taskDelete/{id}" }, method = RequestMethod.GET)
	public String deleteTask(@PathVariable Long id) {
		taskRepo.deleteById(id);
		return "redirect:/tabletasks";
	}
	
	
	// new line
	
	
	@GetMapping("/tasks")
	public String showList(Model model, @RequestParam (defaultValue="0") int page) {
		model.addAttribute("data", taskRepo.findAll( PageRequest.of(page, 4, Sort.by(
			    Order.asc("id")))));
		model.addAttribute("currentPage", page);
		return "redirect:/";
	}
	
	@PostMapping("/tasksave") 
	public String saveTask (AppTask ab) {
		taskRepo.save(ab);
		return "redirect:/";
	}
	
//	@PostMapping("/save") 
//	public String saveTask (@Valid AppTask ab, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//            return "redirect:/";
//        }
//		taskRepo.save(ab);
//		return "redirect:/";
//	}
	
	@GetMapping("/taskdelete") 
	public String deleteAppTask (Long id) {
		taskRepo.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/taskfindOne") 
	@ResponseBody
	public Optional<AppTask> findOne (Long id) {
		return taskRepo.findById(id);
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
