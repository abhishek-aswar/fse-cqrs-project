package com.fse.query.application.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindEmployeeExceptionTest {

	FindEmployeeException employeeException;

	@Test
	void testFindEmployeeException() {
		employeeException = new FindEmployeeException();
		assertNotNull(employeeException);
	}

}
