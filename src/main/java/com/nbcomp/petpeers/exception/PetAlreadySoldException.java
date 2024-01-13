package com.nbcomp.petpeers.exception;

public class PetAlreadySoldException extends RuntimeException{
	public PetAlreadySoldException(String message) {
		super(message);
	}
}
