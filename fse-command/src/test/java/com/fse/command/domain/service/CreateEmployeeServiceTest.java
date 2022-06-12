package com.fse.command.domain.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fse.command.application.dto.CreateEmployeeRequest;
import com.fse.command.application.dto.CreateEmployeeRequest.CreateEmployeeRequestBuilder;
import com.fse.command.domain.converter.EmployeeConverter;
import com.fse.command.domain.model.Employee;
import com.fse.command.domain.model.Employee.EmployeeBuilder;
import com.fse.command.infrasturcture.eventsourcing.KafkaEmployeeCreatedEventSourcing;
import com.fse.command.infrasturcture.eventsourcing.events.EmployeeCreatedEvent;
import com.fse.command.infrasturcture.eventsourcing.events.EmployeeCreatedEvent.EmployeeCreatedEventBuilder;
import com.fse.command.infrasturcture.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class CreateEmployeeServiceTest {

	@InjectMocks
	CreateEmployeeService createEmployeeService;
	
	@Mock
	private EmployeeConverter employeeConverter;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private KafkaEmployeeCreatedEventSourcing kafkaEmployeeCreatedEventSourcing;
	
	@Test
	void createTest() throws JsonProcessingException {
		
		
		CreateEmployeeRequestBuilder createEmployeeRequest = CreateEmployeeRequest.builder().name("Abhishek");
		EmployeeBuilder employee = Employee.builder().id(1).name("Abhishek");
		
		EmployeeCreatedEventBuilder createdEvent = EmployeeCreatedEvent.builder().employee(employee.build());
		
		when(this.employeeConverter.createEmployeeRequestRequestToEmployee(any())).thenReturn(employee.build());
		when(this.employeeRepository.save(employee.build())).thenReturn(null);
		when(this.kafkaEmployeeCreatedEventSourcing.publicCreateEmployeeEvent(employee.build(), anyString())).thenReturn(createdEvent.build());
		
		createEmployeeService.create(createEmployeeRequest.build());
	}

}
