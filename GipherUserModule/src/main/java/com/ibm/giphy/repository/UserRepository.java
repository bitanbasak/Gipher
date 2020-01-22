package com.ibm.giphy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.giphy.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	public void deleteByEmailId(String emailId);
}
