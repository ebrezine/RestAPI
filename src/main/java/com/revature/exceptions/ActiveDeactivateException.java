package com.revature.exceptions;

public class ActiveDeactivateException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ActiveDeactivateException() {
		super();
	}
	
	public ActiveDeactivateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ActiveDeactivateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActiveDeactivateException(String message) {
		super(message);
	}

	public ActiveDeactivateException(Throwable cause) {
		super(cause);
	}
}
