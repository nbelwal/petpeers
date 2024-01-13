package com.nbcomp.petpeers.dto;

import java.util.List;

import com.nbcomp.petpeers.entity.Pet;
import com.nbcomp.petpeers.props.ConstMessages;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
	private Long userId;
	@NotBlank(message = ConstMessages.BLANK_USER_NAME)
	private String userName;
	@NotBlank(message = ConstMessages.BLANK_USER_PASSWORD)
	private String userPassword;
	@NotBlank(message = ConstMessages.BLANK_CONFIRM_PASSWORD)
	private String confirmPassword;
	private List<Pet> pets;
}
