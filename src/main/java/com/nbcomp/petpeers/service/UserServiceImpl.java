package com.nbcomp.petpeers.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nbcomp.petpeers.dto.Login;
import com.nbcomp.petpeers.dto.UserDto;
import com.nbcomp.petpeers.entity.User;
import com.nbcomp.petpeers.exception.InvalidPasswordException;
import com.nbcomp.petpeers.exception.PasswordMismatchException;
import com.nbcomp.petpeers.exception.ResourceNotFoundException;
import com.nbcomp.petpeers.props.ConstMessages;
import com.nbcomp.petpeers.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

private final UserRepo userRepo;
	
	@Override
	public UserDto saveUser(UserDto userDto) {
		if(!userDto.getUserPassword().equals(userDto.getConfirmPassword()))
			throw new PasswordMismatchException(ConstMessages.PASSWORD_MISMATCH);
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		User savedUser = userRepo.save(user);
		return userToUserDto(savedUser);
	}
	
	@Override
	public UserDto authenticateUser(Login login) {
		User user = userRepo.findUserByUserName(login.getUserName())
				.orElseThrow(()-> new ResourceNotFoundException(ConstMessages.RESOURCE_NOT_FOUND_UN,login.getUserName()));
		
		if(!user.getUserPassword().equals(login.getUserPassword())) {
			throw new InvalidPasswordException(ConstMessages.INVALID_PASSWORD);
		}
		return userToUserDto(user);
	}
	
	//helper methods
	
	public UserDto userToUserDto(User user){
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

}
