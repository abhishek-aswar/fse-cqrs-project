package com.fse.command.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fse.command.application.dto.ErrorResponse;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

	@InjectMocks
	CustomExceptionHandler customExceptionHandler;
	
	@Mock
	BindingResult bindingResult;
	
	@Mock
	MethodArgumentNotValidException ex;

	@Test
	void handleAllExceptionsTest() {
		// Given
		Exception ex = new Exception("Error Occured");

		// when
		ResponseEntity<Object> responseEntity = customExceptionHandler.handleAllExceptions(ex, null);

		// then
		ErrorResponse error = (ErrorResponse) responseEntity.getBody();

		assertEquals("Error Occured", error.getDetails().get(0));

	}
	
	/*
	 * @Test void handleMethodArgumentNotValidTest() { // Given
	 * 
	 * BindException exp = new BindException(any());
	 * 
	 * List<ObjectError> errors = new ArrayList<ObjectError>();
	 * 
	 * // when when(ex.getBindingResult()).thenReturn(exp);
	 * when(ex.getBindingResult().getAllErrors()).thenReturn(errors);
	 * 
	 * ResponseEntity<Object> responseEntity =
	 * customExceptionHandler.handleMethodArgumentNotValid(null, null, null, null);
	 * 
	 * // then ErrorResponse error = (ErrorResponse) responseEntity.getBody();
	 * 
	 * }
	 */

}
