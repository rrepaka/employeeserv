package com.paypal.bfs.test.employeeserv.Exceptions;

public class EmployeeNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
