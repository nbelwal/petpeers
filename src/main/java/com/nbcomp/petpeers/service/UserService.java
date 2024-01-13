package com.nbcomp.petpeers.service;

import com.nbcomp.petpeers.dto.Login;
import com.nbcomp.petpeers.dto.UserDto;

public interface UserService {
	UserDto saveUser(UserDto userDto);
	UserDto authenticateUser(Login login);
	
}
