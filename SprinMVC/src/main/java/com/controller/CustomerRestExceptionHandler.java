package com.controller;

//Local Exception handler

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.entity.CustomerErrorResponse;
import com.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
		// CustomerErrorResponse is actual response type and CustomerNotFoundException
		// actual exception type
		// create a StudentErrorResponse

		CustomerErrorResponse error = new CustomerErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		// error is body of the response and HttpStatus.NOT_FOUND is actual status code
	}

	/*
	 * @ExceptionHandler(NumberFormatException.class) public
	 * ResponseEntity<CustomerErrorResponse>
	 * handleNumberFormateException(NumberFormatException exc) { //
	 * CustomerErrorResponse is actual response type and Exception actual exception
	 * // type // create a StudentErrorResponse
	 * 
	 * CustomerErrorResponse error = new CustomerErrorResponse();
	 * 
	 * error.setStatus(HttpStatus.BAD_REQUEST.value());
	 * error.setMessage(exc.getMessage());
	 * error.setTimeStamp(System.currentTimeMillis());
	 * 
	 * // return ResponseEntity
	 * 
	 * return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // error is body
	 * of the response and HttpStatus.NOT_FOUND is actual status code }
	 * 
	 */
	
	// Generic exception which
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
		// CustomerErrorResponse is actual response type and Exception actual exception
		// type
		// create a StudentErrorResponse

		CustomerErrorResponse error = new CustomerErrorResponse();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		// error is body of the response and HttpStatus.NOT_FOUND is actual status code
	}
}
