package com.ibm.giphy.exception;

public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
