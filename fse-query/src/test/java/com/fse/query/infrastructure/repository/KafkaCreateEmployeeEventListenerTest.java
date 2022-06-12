package com.fse.query.infrastructure.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fse.query.domain.model.Employee;
import com.fse.query.domain.model.Employee.EmployeeBuilder;
import com.fse.query.domain.service.FindEmployeeService;
import com.fse.query.infrastructure.eventsourcing.KafkaCreateEmployeeEventListener;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
class KafkaCreateEmployeeEventListenerTest {

	@InjectMocks
	KafkaCreateEmployeeEventListener kafkaCreateEmployeeEventListener;
	
	@Mock
	FindEmployeeService findEmployeeService;
	
	@Mock
	Gson gson;
	
	@Test
	void testListen() throws Exception {
		
		EmployeeBuilder employee = Employee.builder().name("Abhishek");
		
		when(gson.fromJson(anyString(), Employee.class)).thenReturn(employee.build());
		
		doNothing().when(findEmployeeService).createEmployee(employee.build());
		
		kafkaCreateEmployeeEventListener.listen(any());
		
	}

}
