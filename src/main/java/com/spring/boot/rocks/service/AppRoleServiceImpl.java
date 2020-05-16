package com.spring.boot.rocks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.rocks.model.AppRole;
import com.spring.boot.rocks.repository.AppRoleJPARepository;

@Service("roleService")
@Transactional(timeout = 5)
public class AppRoleServiceImpl implements AppRoleService {

	@Autowired
	AppRoleJPARepository appRoleJPARepository;

	@Override
	public Optional<AppRole> findByid(Long id) {
		return appRoleJPARepository.findById(id);
	}

}
