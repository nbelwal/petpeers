package com.nbcomp.petpeers.props;

public final class ConstMessages {
	
	
	private ConstMessages() {
		
	}

	//UserDto validaiton messages
	public static final String BLANK_USER_NAME = "user name can't be blank";
	public static final String BLANK_USER_PASSWORD = "user password can't be blank";
	public static final String BLANK_CONFIRM_PASSWORD = "confirm password can't be blank";
	
	//PetDto validaiton messages
	public static final String BLANK_PET_NAME = "pet name can't be blank";
	public static final String PET_AGE_RANGE = "pet age should be between 0-99";
	
	//BuyRequest validaiton messages
	public static final String BLANK_USER_ID = "user id can't be blank";
	public static final String BLANK_PET_ID = "pet id can't be blank";
	
	//exception messages
	public static final String RESOURCE_NOT_FOUND = "resource with resource id %s not available";
	public static final String PASSWORD_MISMATCH = "user password and confirm is different";
	public static final String INVALID_PASSWORD = "invalid password";
	public static final String RESOURCE_NOT_FOUND_UN = "resource with resource name %s not available";
	public static final String ALREADY_SOLD = "pet is already sold";
	
	//constant used as key for error map
	public static final String KEY_CONSTANT = "message";
	
}
