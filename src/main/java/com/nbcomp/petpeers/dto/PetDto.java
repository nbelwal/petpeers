package com.nbcomp.petpeers.dto;

import org.hibernate.validator.constraints.Range;

import com.nbcomp.petpeers.entity.User;
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
public class PetDto {
	private Long petId;
	@NotBlank(message = ConstMessages.BLANK_PET_NAME)
	private String petName;
	@Range(min = 0, max = 99, message = ConstMessages.PET_AGE_RANGE)
	private Integer petAge;
	private String petPlace;
	private User user;
}
