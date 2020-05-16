package com.spring.boot.rocks.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.rocks.model.AppUser;

@Repository
public interface AppUserJPARepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);

	@Query(nativeQuery = true, value = "SELECT userenabled as label, COUNT(*) as value " + "FROM app_user "
			+ "GROUP BY userenabled")
	public List<ChartData> getUserActiveStatus();
	
	@Query(nativeQuery = true, value = "SELECT username as label, COUNT(*) as value " + "FROM app_user "
			+ "GROUP BY username")
	public List<ChartData> findByRoles();
	
	@Query("select username as label, size(u.appCaseDocumentSet) as value from AppUser u GROUP BY u.username")
	public List<ChartData> getAppuserDocumentCount();
	
	@Query("select username as label, size(u.roles) as value from AppUser u GROUP BY u.username")
	public List<ChartData> getAppuserRoleCount();
	
	@Query("select username, useremail, userfirstname, userlastname from AppUser")
	List<AppUser> getOnlyFourFields();

	@Query("select u from AppUser u where datediff(curdate(),u.userdatecreated)>=180")
	List<AppUser> findByUserdatecreatedMoreThanSixMonths();

	@Query("select u from AppUser u where datediff(currentDate,u.userdatecreated)>=180")
	List<AppUser> findByUserdatecreatedMoreThanSixMonthsCustom(@Param("currentDate") Date currentDate);

	@Query("select u from AppUser u where datediff(curdate(),u.userdatecreated)>=365")
	List<AppUser> findByUserdatecreatedMoreThanOneYear();

	@Query("select u from AppUser u where datediff(curdate(),u.userdatecreated)>=3650")
	List<AppUser> findByUserdatecreatedMoreThanTenYears();

	@Query("select u from AppUser u where datediff(curdate(),u.userdatecreated)>=7300")
	List<AppUser> findByUserdatecreatedMoreThanTwentyYears();

	@Query("select count(aud) from AppUser au join au.appCaseDocumentSet aud where au = ?1")
    Long countAppuserdocumentByAppuser(AppUser appUser);
	
	@Query("select count(aud) from AppUser au join au.appCaseDocumentSet aud where au.id = ?1")
    Long countAppuserdocumentById(Long id);
	
	@Query("select size(u.appCaseDocumentSet) from AppUser u where u.id=:id")
	int findAllAppuserdocumentset(Long id);
//	int findAllChildrenCount(@Param("id")Long id);
	

	@Query("SELECT u FROM AppUser u WHERE u.userdatecreated < ?1")
	List<AppUser> findByUserdatecreated(Date userdatecreated);
	
	@Query("SELECT u FROM AppUser u WHERE u.username LIKE %:username%")
	List<AppUser> findByUsernameLike(String username);
	
	List<AppUser> findByUsernameIgnoreCaseContaining(String username);

	@Query("SELECT u.id FROM AppUser u WHERE u.username = ?1")
	AppUser getAppuserIdByUsername(String username);
	
	@Query("SELECT u FROM AppUser u WHERE u.id = ?1")
	AppUser getAppuserByAppUserId(Long id);
	
	AppUser findByUseremail(String useremail);

	AppUser findByUserconfirmationtoken(String userconfirmationToken);

	List<AppUser> findByUserenabledTrue();
	
	List<AppUser> findByUserenabledFalse();
	
	
	
	@Query(value="SELECT * FROM app_user WHERE rownum<=3 ORDER BY username DESC", nativeQuery=true)
	List<AppUser> findTop3ByUsernamenoLessThanOrderByIdAsc();
//	List<AppUser> findTop7ByUsernamenoLessThanOrderByUsernameDesc();
//	List<AppUser> findTop7ByUsernamenoLessThanOrderByIdAsc();
	
	
	@Modifying
	@Transactional
	@Query("UPDATE AppUser au set au.userenabled = true where au.id = ?1")
	void setAppUserAsActiveById(Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE AppUser au set au.userenabled = false where au.id = ?1")
	void setAppUserAsInActiveById(Long id);
	
//	@Modifying
//    @Transactional
//    @Query("UPDATE AppUser au SET au.userenabled = true")
//    void setEnabledFalse();
	
//	@Query("select t from Test t join User u where u.username = :username")
//	List<Test> findAllByUsername(@Param("username")String username);
	
//	@Query("select user from AppUser u where datediff(curdate(),curdate(),u.userdatecreated)>=180")

	// 3 minutes
//	SELECT m FROM Mail m WHERE m.sentAt < sysdate - 3/(24*60)
//	@Query("SELECT u FROM AppUser u WHERE u.userdatecreated  < sysdate - 3/(24*60)")

//	@Query("select u FROM AppUser u WHERE u.userdatecreated <= :userdatecreated")
//	List<AppUser> findByUserdatecreated(Date userdatecreated);
	
//	List<AppUser> findByUsernameIgnoreCase(String username);

}
