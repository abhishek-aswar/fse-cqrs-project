package com.fse.query.domain.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeNotFoundExceptionTest {

	EmployeeNotFoundException employeeNotFoundException;

	@Test
	void testEmployeeNotFoundException() {
		employeeNotFoundException = new EmployeeNotFoundException("test1", "test2");
		assertNotNull(employeeNotFoundException);
	}

}
