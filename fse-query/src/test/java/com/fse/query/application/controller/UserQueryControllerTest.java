package com.fse.query.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fse.query.application.dto.EmployeeResponse;
import com.fse.query.application.dto.EmployeeResponse.EmployeeResponseBuilder;
import com.fse.query.domain.exception.EmployeeNotFoundException;
import com.fse.query.domain.model.Employee;
import com.fse.query.domain.model.Employee.EmployeeBuilder;
import com.fse.query.domain.service.FindEmployeeService;

@ExtendWith(MockitoExtension.class)
class UserQueryControllerTest {

	@InjectMocks
	UserQueryController userQueryController;

	@Mock
	FindEmployeeService findEmployeeService;

	@Test
	void findEmployeeTest() throws EmployeeNotFoundException {
		EmployeeResponseBuilder employeeResponseBuilder = EmployeeResponse.builder().name("Abhishek");
		// when
		when(this.findEmployeeService.findByName(anyString())).thenReturn(employeeResponseBuilder.build());
		EmployeeResponse response = userQueryController.findEmployee("Abhishek");
		assertEquals(response.getName(), employeeResponseBuilder.build().getName());
	}

	@Test
	void employeeSearchTest() {
		EmployeeBuilder employee = Employee.builder().name("Abhishek");

		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee.build());

		// when
		//when(this.findEmployeeService.findPracticeQuestionByUserIdAndQuestionsQuestionID(anyString(), anyString(), anyString(), anyString())).thenReturn(employeeList);

		List<Employee> response = userQueryController.employeeSearch("", "", "", "");

		assertEquals(response.get(0).getName(), employee.build().getName());
	}

	@Test
	void myEmployeeDetailsFallBackTest() {
		EmployeeResponseBuilder employeeResponseBuilder = EmployeeResponse.builder().name("Abhishek");
		EmployeeResponse response = userQueryController.myEmployeeDetailsFallBack("Abhishek", null);
		assertEquals(response.getName(), employeeResponseBuilder.build().getName());
	}

}
