package com.nbcomp.petpeers.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nbcomp.petpeers.props.ConstMessages;

@RestControllerAdvice
public class AppExHandler {
	private static String messageKey = ConstMessages.KEY_CONSTANT;
	 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public Map<String, String> handleInvalidArgument(SQLIntegrityConstraintViolationException ex) {
		return createErrorMap(ex);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidPasswordException.class)
	public Map<String, String> handleInvalidPassword(InvalidPasswordException ex) {
		return createErrorMap(ex);
	}
 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<String, String> handleUserNotFound(ResourceNotFoundException ex) {
		return createErrorMap(ex);
	}
 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PasswordMismatchException.class)
	public Map<String, String> handlePasswordMismatch(PasswordMismatchException ex) {
		return createErrorMap(ex);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PetAlreadySoldException.class)
	public Map<String, String> handlePetAlreadySold(PetAlreadySoldException ex) {
		return createErrorMap(ex);
	}
 
	// helper methods
	private Map<String, String> createErrorMap(Exception ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(messageKey, ex.getMessage());
		return errorMap;
	}
}
