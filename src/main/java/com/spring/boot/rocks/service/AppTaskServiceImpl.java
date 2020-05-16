package com.spring.boot.rocks.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.boot.rocks.model.AppTask;
import com.spring.boot.rocks.repository.AppTaskRepository;

@Service
public class AppTaskServiceImpl implements AppTaskService{

	@Autowired
	AppTaskRepository taskRepo;
	
	@Override
	public List<AppTask> findAllTasks() {
		// TODO Auto-generated method stub
		return taskRepo.findAll();
	}

	@Override
	public Optional<AppTask> findById(Long id) {
		// TODO Auto-generated method stub
		return taskRepo.findById(id);
	}

	@Override
	public AppTask saveAppTask(AppTask appTask) {
		// TODO Auto-generated method stub
		appTask.setTaskname(appTask.getTaskname());
		appTask.setTaskdescription(appTask.getTaskdescription());
		appTask.setTaskunitnumber(appTask.getTaskunitnumber());
		appTask.setTaskunit(appTask.getTaskunit());
		appTask.setUsers(appTask.getUsers());
		appTask.setTaskdatecreated(new Date());
		appTask.setTaskcreatedby(getPrincipal());
		appTask.setTaskdatemodified(null);
		appTask.setTaskmodifiedby(null);
		appTask.setTaskcompleted(false);
		return taskRepo.save(appTask);
	}

	@Override
	public AppTask updateAppTask(AppTask appTask) {
		AppTask entity = taskRepo.findById(appTask.getId()).orElse(null);
		if (entity != null) {
			entity.setTaskname(appTask.getTaskname());
			entity.setTaskdescription(appTask.getTaskdescription());
			entity.setTaskunitnumber(appTask.getTaskunitnumber());
			entity.setTaskunit(appTask.getTaskunit());
			entity.setUsers(appTask.getUsers());
			entity.setTaskdatecreated(entity.getTaskdatecreated());
			entity.setTaskcreatedby(entity.getTaskcreatedby());
			entity.setTaskdatemodified(new Date());
			entity.setTaskmodifiedby(getPrincipal());
			entity.setTaskcompleted(appTask.isTaskcompleted());
		}
		return taskRepo.save(entity);
	}

	@Override
	public void deleteAppTask(Long id) {
		// TODO Auto-generated method stub
		taskRepo.deleteById(id);
		
	}

	@Override
	public AppTask findByTaskname(String taskname) {
		// TODO Auto-generated method stub
		return taskRepo.findByTaskname(taskname);
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
	public AppTask updateTaskStatusComplete(Long id) {
		// TODO Auto-generated method stub
		return taskRepo.setCompleteStatusForTask(id);
	}
	
	@Override
	public AppTask updateTaskStatusInComplete(Long id) {
		// TODO Auto-generated method stub
		return taskRepo.setInCompleteStatusForTask(id);
	}

	
}
