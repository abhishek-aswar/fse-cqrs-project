/**
 * 
 */
package com.fse.command.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fse.command.application.dto.CreateEmployeeRequest;
import com.fse.command.application.dto.CreateEmployeeRequest.CreateEmployeeRequestBuilder;
import com.fse.command.domain.model.Employee;
import com.fse.command.domain.model.Employee.EmployeeBuilder;
import com.fse.command.domain.service.CreateEmployeeService;
import com.fse.command.infrasturcture.eventsourcing.events.EmployeeCreatedEvent;
import com.fse.command.infrasturcture.eventsourcing.events.EmployeeCreatedEvent.EmployeeCreatedEventBuilder;

@ExtendWith(MockitoExtension.class)
public class UserCommandControllerTest {

	@InjectMocks
	UserCommandController employeeController;

	@Mock
	private CreateEmployeeService createEmployeeService;

	@Test
	public void getHello() throws Exception {
		
		// given
		CreateEmployeeRequestBuilder reqCreateEmployeeRequestBuilder = CreateEmployeeRequest.builder().name("Abhishek");
		EmployeeBuilder employee = Employee.builder().id(1).name("Abhishek").email("abhi@gmail.com");
		EmployeeCreatedEventBuilder employeeCreatedEvent = EmployeeCreatedEvent.builder().uuid(UUID.randomUUID()).employee(employee.build());

		// when
		when(this.createEmployeeService.create(any())).thenReturn(employeeCreatedEvent.build());

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		EmployeeCreatedEvent empCreatedEvent = employeeController.newEmployee(reqCreateEmployeeRequestBuilder.build());

		// Then
		assertEquals("Abhishek", empCreatedEvent.getEmployee().getName());
		
	}

}