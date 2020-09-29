package com.paypal.bfs.test.employeeserv.Exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BusinessException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger("BusinessException") ;
	
	
	public BusinessException(String message) {
		super(message);
		log(this);
	}
	
	private void log(Exception e) {
		e.printStackTrace();
		logger.log(Level.SEVERE, "Business Error: " + e.getClass().getName() + ":" + e.getMessage());
	}


}
