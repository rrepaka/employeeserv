package com.paypal.bfs.test.employeeserv.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.employeeserv.Entity.EmployeeDO;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeDO, Integer>{
	
	//Optional<EmployeeDO> findByEmployeeId(Integer id);
	//void  createEmployee(EmployeeDO employeeDO);
	

}
