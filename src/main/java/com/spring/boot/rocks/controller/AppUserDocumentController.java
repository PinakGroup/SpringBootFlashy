package com.spring.boot.rocks.controller;

import java.io.IOException;
//import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

//import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.model.AppUserDocument;
import com.spring.boot.rocks.model.AppUserDocumentBucket;
import com.spring.boot.rocks.repository.AppUserDocumentRepository;
import com.spring.boot.rocks.repository.AppUserJPARepository;
import com.spring.boot.rocks.service.AppUserDocumentService;
import com.spring.boot.rocks.validator.AppUserDocumentValidator;

@Controller
@RequestMapping("/")
public class AppUserDocumentController {

	@Autowired
	AppUserJPARepository appuserService;

	@Autowired
	AppUserDocumentService appuserDocumentService;

	@Autowired
	AppUserDocumentRepository appUserDocumentRepository;

	@Autowired
	MessageSource messageSource;

	@Autowired
	AppUserDocumentValidator fileValidator;

	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@RequestMapping(value = { "adddocument/{id}" }, method = RequestMethod.GET)
	public String addDocuments(@PathVariable("id") Long userId, ModelMap model) {
		Optional<AppUser> appuser = appuserService.findById(userId);
		model.addAttribute("appuser", appuser);

		AppUserDocumentBucket fileModel = new AppUserDocumentBucket();
		model.addAttribute("fileBucket", fileModel);

		List<AppUserDocument> documents = appuserDocumentService.findAllByUserId(userId);

		for (AppUserDocument aud : documents) {
			aud.getAppuserdocumentdescription();
		}

		model.addAttribute("documents", documents);
		model.addAttribute("metaTitle", "Add Documents");
		return "tabledocuments";
	}

	@RequestMapping(value = { "adddocument/{id}" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid AppUserDocumentBucket fileBucket, BindingResult result, ModelMap model,
			@PathVariable("id") Long appuserId) throws IOException {
		model.addAttribute("metaTitle", "Home");
		if (result.hasErrors()) {
			System.out.println("validation errors");
			AppUser appuser = appuserService.getOne(appuserId);
			model.addAttribute("appuser", appuser);
			model.addAttribute("userid", appuser.getId());
			List<AppUserDocument> documents = appuserDocumentService.findAllByUserId(appuserId);
			model.addAttribute("documents", documents);
			return "casedocuments";
		} else {
			AppUser appuser = appuserService.getOne(appuserId);
			model.addAttribute("appuser", appuser);
			saveDocument(fileBucket, appuser);
			return "redirect:/adddocument/" + appuserId;

//			return "tabledocuments";
		}
		
	}

	@RequestMapping(value = { "downloaddocument/{id}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable("id") Long docId, HttpServletResponse response, ModelMap model)
			throws IOException {
		AppUserDocument document = appuserDocumentService.findById(docId);
		response.setContentType(document.getAppuserdocumenttype());
		response.setContentLength(document.getAppuserdocumentcontent().length);
		response.setHeader("Content-Disposition", "inline; filename=\"" + document.getAppuserdocumentname() + "\"");
		try {
			FileCopyUtils.copy(document.getAppuserdocumentcontent(), response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/deleteuserdocument/{docid}/{userid}" }, method = RequestMethod.GET)
	public String deleteUserDocument(@PathVariable("docid") Long docid, @PathVariable("userid") Long userid) {
		appUserDocumentRepository.deleteById(docid);
		// appUserDocumentRepository.deleteByIdandAppuser(docid, userid);
		return "redirect:/adddocument/" + userid;
	}

	private void saveDocument(AppUserDocumentBucket fileBucket, AppUser appuser) throws IOException {

		AppUserDocument document = new AppUserDocument();

		MultipartFile multipartFile = fileBucket.getFile();

		document.setAppuserdocumentname(multipartFile.getOriginalFilename());
		document.setAppuserdocumentdescription(fileBucket.getAppuserdocumentdescription());
		document.setAppuserdocumenttype(multipartFile.getContentType());
		document.setAppuserdocumentcontent(multipartFile.getBytes());
		document.setAppUser(appuser);
		appuserDocumentService.saveDocument(document);
	}
}