package com.fse.command.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fse.command.application.dto.CreateEmployeeRequest;
import com.fse.command.domain.service.CreateEmployeeService;
import com.fse.command.infrasturcture.eventsourcing.events.EmployeeCreatedEvent;

@RestController
public class UserCommandController {

	@Autowired
	private CreateEmployeeService createEmployeeService;

	@PostMapping("create-employee")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public EmployeeCreatedEvent newEmployee(@Valid @RequestBody CreateEmployeeRequest req) {
		return createEmployeeService.create(req);
	}
	
	@PostMapping("update-employee")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public EmployeeCreatedEvent updateEmployee(@Valid @RequestBody CreateEmployeeRequest req) {
		return createEmployeeService.updateEmp(req);
	}
}
