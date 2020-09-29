package com.paypal.bfs.test.employeeserv.businessImpl;

import com.paypal.bfs.test.employeeserv.Entity.EmployeeDO;
import com.paypal.bfs.test.employeeserv.Exceptions.EmployeeNotFoundException;

public interface EmployeeService {
	
	public EmployeeDO findByEmployeeId(Integer id) throws EmployeeNotFoundException;
	public void createEmployee(EmployeeDO employeeDAO);
	

}
