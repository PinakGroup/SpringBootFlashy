package com.spring.boot.rocks.service;

import java.util.List;
import java.util.Optional;

import com.spring.boot.rocks.model.AppTask;

public interface AppTaskService {

    List<AppTask> findAllTasks();

    Optional<AppTask> findById(Long id);

    AppTask saveAppTask(AppTask appTask);
    
    AppTask updateAppTask(AppTask appTask);

    void deleteAppTask(Long id);

	AppTask  findByTaskname(String taskName);
	
	AppTask updateTaskStatusComplete(Long id);
	
	AppTask updateTaskStatusInComplete(Long id);
	
}
