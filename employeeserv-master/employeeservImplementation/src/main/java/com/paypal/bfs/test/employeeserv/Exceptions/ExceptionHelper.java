package com.paypal.bfs.test.employeeserv.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@ControllerAdvice
public class ExceptionHelper {
	@ExceptionHandler(value = { InvalidInputException.class })
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Unauthorized.class })
	public ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { BusinessException.class })
	public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
