package com.spring.boot.rocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.model.AppUserDocument;

@Repository
public interface AppUserDocumentRepository extends JpaRepository<AppUserDocument, Long> {
	
//	AppUserDocument findByCasedocumentname(String casedocumentname);

	@Query("select c from AppUserDocument c where lower(c.appUser) = lower(:appUser)")
	public List<AppUserDocument> findByUserid(@Param("appUser") long appUser);
	
//	  Optional<AppUserDocument> findByIdandUserid(Long id, Long postId);
	  
	  @Query("SELECT aud FROM AppUserDocument aud WHERE aud.id = ?1 AND aud.appUser = ?2")
	  AppUserDocument findByIdandAppuser (Long id, Long userid);
	  
	  @Query("SELECT aud FROM AppUserDocument aud WHERE aud.id = ?1 AND aud.appUser = ?2")
	  AppUserDocument deleteByIdandAppuser (Long id, Long userid);

}
