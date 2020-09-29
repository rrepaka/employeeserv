package com.paypal.bfs.test.employeeserv.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
	@RequestMapping(value = "/v1/bfs/employees/{id}", produces = "application/json", consumes="application/json", method = RequestMethod.GET)
	ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);

	// ----------------------------------------------------------
	// TODO - add a new operation for creating employee resource.
	// ----------------------------------------------------------

	@RequestMapping(value = "/v1/bfs/employees/create", produces = "application/json", consumes="application/json", method = RequestMethod.POST)
	ResponseEntity<Integer> createEmployee(@RequestBody Employee employee);
}
