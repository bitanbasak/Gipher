package com.ibm.giphy.exception;

public class GifNotFoundException extends Exception {

	public GifNotFoundException() {
		super();
	}

	public GifNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GifNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GifNotFoundException(String message) {
		super(message);
	}

	public GifNotFoundException(Throwable cause) {
		super(cause);
	}
}
