package com.exception;

public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CustomerNotFoundException() {
		super();
	
	}

	public CustomerNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	
	}

	public CustomerNotFoundException(String message) {
		super(message);

	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);

	}
	

}
