package com.spring.boot.rocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.model.AppTask;

@Repository
public interface AppTaskRepository extends JpaRepository<AppTask, Long> {

	AppTask findByTaskname(String taskname);

	List<AppTask> findByTasknameLike(String taskname);

	List<AppTask> findByTasknameIgnoreCaseContaining(String taskname);
	
	@Modifying
	@Query("update AppTask task set task.taskcompleted = 1 where task.id = ?1")
	AppTask setCompleteStatusForTask(Long id);
	
	@Modifying
	@Query("update AppTask task set task.taskcompleted = 1 where task.id = ?1")
	AppTask setInCompleteStatusForTask(Long id);

}
