package com.paypal.bfs.test.employeeserv.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.Entity.AddressDO;
import com.paypal.bfs.test.employeeserv.Entity.EmployeeDO;
import com.paypal.bfs.test.employeeserv.Exceptions.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.Exceptions.InvalidInputException;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.businessImpl.EmployeeService;
import com.paypal.bfs.test.employeeserv.businessImpl.ValidationResult;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {
	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<Employee> employeeGetById(String id) {
		ResponseEntity<Employee> response = null;
		Integer employeeId = null;
		EmployeeDO employeeDAO = null;
		//
		try {
			employeeId = Integer.valueOf(id);
		} catch (NumberFormatException ex) {
			new InvalidInputException(" Invalid number format employee id:" + id);
		}
		//
		employeeDAO = employeeService.findByEmployeeId(employeeId);
		if (employeeDAO == null) {
			throw new EmployeeNotFoundException(MessageFormat.format("Employee Id {0} not found.", id));
		}
		//

		Employee employee = adaptEmployeeDAOToEmployee(employeeDAO);
		response = new ResponseEntity<>(employee, HttpStatus.OK);
		return response;
	}

	@Override
	public ResponseEntity<Integer> createEmployee(Employee employee) {
		ValidationResult result = validate(employee);
		if (!result.getSuccess()) {
			new InvalidInputException(result.getExplanation());
		}
		//
		EmployeeDO dao = new EmployeeDO();
		dao.setId(employee.getId());
		dao.setFirstName(employee.getFirstName());
		dao.setLastName(employee.getLastName());
		// Address
		Address address = employee.getAddress();
		AddressDO addressDO = new AddressDO();
		addressDO.setLine1(address.getLine1());
		addressDO.setLine2(address.getLine2());
		addressDO.setCity(address.getCity());
		//
		addressDO.setState(address.getState());
		addressDO.setCountry(address.getCountry());
		addressDO.setZipCode(address.getZipCode());
		
		//Note : For Idempotency logic  If id is already existing , then we will return the existing object without checking field by field.
		employeeService.createEmployee(dao);

		return new ResponseEntity<Integer>(employee.getId(), HttpStatus.OK);
	}

	private Employee adaptEmployeeDAOToEmployee(EmployeeDO employeeDAO) {
		Employee employee = new Employee();
		//
		employee.setId(employeeDAO.getId());
		employee.setFirstName(employeeDAO.getFirstName());
		employee.setLastName(employeeDAO.getLastName());

		AddressDO addressDO = employeeDAO.getAddressDO();
		// Address
		Address address = new Address();
		address.setLine1(addressDO.getLine1());
		address.setLine2(addressDO.getLine2());
		address.setCity(addressDO.getCity());
		//
		address.setState(addressDO.getState());
		address.setCountry(addressDO.getCountry());
		address.setZipCode(addressDO.getZipCode());

		return employee;
	}

	private ValidationResult validate(Employee employee) {
		ValidationResult result = new ValidationResult("OK", Boolean.TRUE);
		if (employee == null) {
			return new ValidationResult("Employee cannot be null", Boolean.FALSE);
		}
		String firstName = employee.getFirstName();
		if (firstName == null || firstName.isEmpty()) {
			return new ValidationResult("FirstName cannot be empty or null", Boolean.FALSE);
		}
		String lastName = employee.getLastName();
		if (lastName == null || lastName.isEmpty()) {
			return new ValidationResult("LastName cannot be empty or null ", Boolean.FALSE);
		}

		// Address
		Address address = employee.getAddress();
		String line1 = address.getLine1();
		if (line1 == null || line1.isEmpty()) {
			return new ValidationResult("Line1 cannot be empty or null in address", Boolean.FALSE);
		}

//		String line2 = address.getLine2();
//		if (line2 == null || line2.isEmpty()) {
//			return new ValidationResult("Line2 cannot be empty or null in address", Boolean.FALSE);
//		}

		String city = address.getCity();
		if (city == null || city.isEmpty()) {
			return new ValidationResult("City cannot be empty or null in address", Boolean.FALSE);
		}

		String state = address.getState();
		if (state == null || state.isEmpty()) {
			return new ValidationResult("State cannot be empty or null in address", Boolean.FALSE);
		}

		String country = address.getCountry();
		if (country == null || country.isEmpty()) {
			return new ValidationResult("Country cannot be empty or null in address", Boolean.FALSE);
		}

		Integer zipCode = address.getZipCode();
		if (zipCode == null) {
			return new ValidationResult("Zip Code cannot be empty or null in address", Boolean.FALSE);
		}

		if (zipCode < 0) {
			return new ValidationResult("Zip Code cannot be Negative value in address", Boolean.FALSE);
		}

		return result;
	}

}
