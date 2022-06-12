package com.fse.query.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fse.query.application.dto.EmployeeResponse;
import com.fse.query.application.exception.FindEmployeeException;
import com.fse.query.domain.exception.EmployeeNotFoundException;
import com.fse.query.domain.model.Employee;
import com.fse.query.domain.service.FindEmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class UserQueryController {

    @Autowired
    private FindEmployeeService findEmployeeService;

    //@GetMapping("employee/{name}")
    @GetMapping("employee")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = "detailsForEmployeeSupportApp", fallbackMethod="myEmployeeDetailsFallBack")
    public EmployeeResponse findEmployee(@RequestBody String name) {
        try{
            return findEmployeeService.findByName(name);
        }catch (EmployeeNotFoundException ex) {
            log.error(ex);
            throw new FindEmployeeException();
        }
    }
    
	@GetMapping("employeeSearch")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> employeeSearch(@RequestParam(required = false) String name, @RequestParam(required = false) String email, @RequestParam(required = false) String mobile, @RequestParam(required = false) String skillName) {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			empList = findEmployeeService.employeeSearch(name, email, mobile, skillName);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		return empList;
	}
    
	public EmployeeResponse myEmployeeDetailsFallBack(String name, Throwable t) {
		return EmployeeResponse.builder().name(name).build();
	}
    
}
