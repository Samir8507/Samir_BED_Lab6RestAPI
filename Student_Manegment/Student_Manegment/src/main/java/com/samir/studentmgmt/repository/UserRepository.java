package com.samir.studentmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samir.studentmgmt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.username = ?1")
	public User getUserByUsername(String username);

}
