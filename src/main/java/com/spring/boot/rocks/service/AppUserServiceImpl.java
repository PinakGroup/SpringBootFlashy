package com.spring.boot.rocks.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppUserJPARepository;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserJPARepository appUserRepository;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Override
//	public AppUser findById(long id) {
//		return appUserRepository.findById(id);
//	}
    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
	public AppUser findByUseremail(String useremail) {
		return appUserRepository.findByUseremail(useremail);
	}
    
    @Override
	public AppUser findByUserconfirmationtoken(String userconfirmationtoken) {
		return appUserRepository.findByUserconfirmationtoken(userconfirmationtoken);
	}
    
    
    @Override
    public AppUser saveAppUser(AppUser appUser) {
    	
    	appUser.setUsername(appUser.getUsername());
    	appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
    	appUser.setUseremail(appUser.getUseremail());
    	appUser.setUserfirstname(appUser.getUserfirstname());
    	appUser.setUserlastname(appUser.getUserlastname());
    	appUser.setUseraddress(appUser.getUseraddress());
    	appUser.setUserenabled(false);
    	appUser.setUserconfirmationtoken(UUID.randomUUID().toString());
    	appUser.setUsercreatedby(getPrincipal());
    	appUser.setUserdatecreated(new Date());
    	appUser.setUsermodifiedby(null);
    	appUser.setUserdatemodified(null);
    	appUser.setRoles(appUser.getRoles());
        return appUserRepository.save(appUser);
    }
    
    @Override
    public AppUser updateAppUser(AppUser appUser) {
    	AppUser entity = appUserRepository.findById(appUser.getId()).orElse(null);
		if (entity != null) {
			
			entity.setUsername(entity.getUsername());
			entity.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
			entity.setUseremail(entity.getUseremail());
			entity.setUserfirstname(appUser.getUserfirstname());
			entity.setUserlastname(appUser.getUserlastname());
			entity.setUseraddress(appUser.getUseraddress());
			entity.setRoles(appUser.getRoles());
			entity.setUserenabled(appUser.isUserenabled());
			entity.setUsercreatedby(entity.getUsercreatedby());
			entity.setUserdatecreated(entity.getUserdatecreated());
			entity.setUsermodifiedby(getPrincipal());
			entity.setUserdatemodified(new Date());
		}
        return appUserRepository.save(entity);
    }

    @Override
    public void deleteAppUser(Long id) {
    	appUserRepository.deleteById(id);
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

	@Override
	public AppUser findByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public Optional<AppUser> delete(Long id){
		Optional<AppUser> sPhone = get(id);
		appUserRepository.deleteById(id);
		return sPhone;
	}

	private Optional<AppUser> get(Long id) {
		return appUserRepository.findById(id);
	}

	
}
