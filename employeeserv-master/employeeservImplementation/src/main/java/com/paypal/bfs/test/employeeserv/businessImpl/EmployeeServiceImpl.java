package com.paypal.bfs.test.employeeserv.businessImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.employeeserv.Entity.EmployeeDO;
import com.paypal.bfs.test.employeeserv.Exceptions.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.dao.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// @Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theemployeeRepository) {
		employeeRepository = theemployeeRepository;
	}

	@Override
	public EmployeeDO findByEmployeeId(Integer id) throws EmployeeNotFoundException {
		Optional<EmployeeDO> optionalEmployee = employeeRepository.findById(id);
		return optionalEmployee.get();

	}

	@Override
	public void createEmployee(EmployeeDO employeeDAO) {

		Integer eemployeeId = employeeDAO.getId();
		if (!employeeRepository.existsById(eemployeeId)) {
			employeeRepository.save(employeeDAO);
		}
	}

}
