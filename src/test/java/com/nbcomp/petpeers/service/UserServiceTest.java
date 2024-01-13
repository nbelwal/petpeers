package com.nbcomp.petpeers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.nbcomp.petpeers.dto.UserDto;
import com.nbcomp.petpeers.entity.User;
import com.nbcomp.petpeers.exception.PasswordMismatchException;
import com.nbcomp.petpeers.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void saveUser() {
		UserDto userDto = UserDto.builder().userName("neeraj").userPassword("1234").confirmPassword("1234").build();
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		User savedUser = new User();
		BeanUtils.copyProperties(user, savedUser);
		savedUser.setUserId(1l);
		when(userRepo.save(user)).thenReturn(savedUser);
		UserDto saveUser = userService.saveUser(userDto);
		assertEquals(saveUser.getUserName(), userDto.getUserName());
	}

	@Test
	void saveUserNeg() {
		UserDto userDto = UserDto.builder().userId(1l).userName("neeraj").userPassword("123").confirmPassword("1234")
				.build();
		assertThrows(PasswordMismatchException.class, () -> userService.saveUser(userDto));
	}

	//private method testing
	
	@Test
	void userToUserDto() {
		User user = User.builder().userId(1l).build();
		UserDto userDto = userService.userToUserDto(user);
		assertEquals(user.getUserId(), userDto.getUserId());
	}
}
