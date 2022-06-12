package com.fse.command.infrasturcture.eventsourcing;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fse.command.domain.model.Employee;
import com.fse.command.domain.model.Employee.EmployeeBuilder;
import com.fse.command.infrasturcture.eventsourcing.events.EmployeeCreatedEvent;

import lombok.val;

@ExtendWith(MockitoExtension.class)
class KafkaEmployeeCreatedEventSourcingTest {

	@InjectMocks
	KafkaEmployeeCreatedEventSourcing employeeCreatedEventSourcing;

	@Mock
	KafkaTemplate<String, String> kafkaTemplate;

	@Test
	void publicCreateEmployeeEventTest() throws JsonProcessingException {

		EmployeeBuilder employeeBuilder = Employee.builder().id(1).name("Abhishek");
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        val json = objectWriter.writeValueAsString(employeeBuilder.build());
		
		when(kafkaTemplate.send(null, json)).thenReturn(null);
		EmployeeCreatedEvent employeeCreatedEvent = employeeCreatedEventSourcing
				.publicCreateEmployeeEvent(employeeBuilder.build(), anyString());
	}

}
