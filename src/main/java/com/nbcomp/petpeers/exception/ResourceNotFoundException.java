package com.nbcomp.petpeers.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String messageBody, String parameter) {
		super(String.format(messageBody,parameter));
	}
}
