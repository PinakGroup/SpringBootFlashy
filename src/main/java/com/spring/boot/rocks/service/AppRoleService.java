package com.spring.boot.rocks.service;

import java.util.Optional;

import com.spring.boot.rocks.model.AppRole;

public interface AppRoleService {

	Optional<AppRole> findByid(Long id);

}
