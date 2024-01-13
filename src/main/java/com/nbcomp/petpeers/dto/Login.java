package com.nbcomp.petpeers.dto;

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
public class Login {
	@NotBlank(message = ConstMessages.BLANK_USER_NAME)
	private String userName;
	@NotBlank(message = ConstMessages.BLANK_USER_PASSWORD)
	private String userPassword;
}
