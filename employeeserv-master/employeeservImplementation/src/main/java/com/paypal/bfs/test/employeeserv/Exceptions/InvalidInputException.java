package com.paypal.bfs.test.employeeserv.Exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InvalidInputException extends Exception {
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger("InvalidInputException") ;

	public InvalidInputException(String message) {
		super(message);
		log(this);
	}

	private void log(Exception e) {
		e.printStackTrace();
		logger.log(Level.WARNING, "Input Error: " + e.getClass().getName() + ":" + e.getMessage());
	}

}
