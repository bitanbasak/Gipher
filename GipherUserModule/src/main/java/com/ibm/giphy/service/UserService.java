package com.ibm.giphy.service;

import org.springframework.http.ResponseEntity;
import com.ibm.giphy.exception.UserAlreadyExistsException;
import com.ibm.giphy.model.User;

public interface UserService {
	public User registerUser(User user) throws UserAlreadyExistsException;
	public User authenticateUser(String emailId,String password);
	public boolean isAuthenticated();
	public User getByEmail(String email);
	public boolean removeUser(String emailId);
	public ResponseEntity<User> getUserByEmail(String emailId);
	public User updateUser(User user);
}
