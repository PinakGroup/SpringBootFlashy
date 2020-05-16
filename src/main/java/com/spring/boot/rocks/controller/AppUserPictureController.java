package com.spring.boot.rocks.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.rocks.model.AppUserProfile;
import com.spring.boot.rocks.repository.AppUserProfileRepository;
import com.spring.boot.rocks.service.AppUserProfilePictureService;
import com.spring.boot.rocks.service.AppUserProfileService;


@Controller
public class AppUserPictureController {

    private final AppUserProfilePictureService appUserProfileProfilePictureService;
    private final AppUserProfileService appUserProfileProfileService;

    @Autowired
    AppUserProfileRepository appUserProfileProfileRepository;
    
    public AppUserPictureController(AppUserProfilePictureService appUserProfileProfilePictureService, AppUserProfileService appUserProfileProfileService) {
        this.appUserProfileProfilePictureService = appUserProfileProfilePictureService;
        this.appUserProfileProfileService = appUserProfileProfileService;
    }

    @GetMapping("appUserProfile/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model){
        model.addAttribute("appUserProfile", appUserProfileProfileService.findById(id));

        return "imageupload";
    }

    @PostMapping("appUserProfile/{id}/image")
    public String handleImagePost(@PathVariable Long id, @RequestParam("userPicturefile") MultipartFile file){

        appUserProfileProfilePictureService.saveUserPictureFile(Long.valueOf(id), file);

        return "redirect:/profile";
    }

    @GetMapping("appUserProfile/{id}/appUserProfileimage")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
    	AppUserProfile appUserProfile = appUserProfileProfileService.findById(id);

        if (appUserProfile.getProfilepic() != null) {
            byte[] byteArray = new byte[appUserProfile.getProfilepic().length];
            int i = 0;

            for (Byte wrappedByte : appUserProfile.getProfilepic()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
    
    
}
