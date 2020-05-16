package com.spring.boot.rocks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.model.AppRole;

@Repository
public interface AppRoleJPARepository extends JpaRepository<AppRole, Long> {

	Optional<AppRole> findById(Long id);

	AppRole findByname(String rolename);
}
