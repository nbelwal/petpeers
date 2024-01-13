package com.nbcomp.petpeers.dto;

import com.nbcomp.petpeers.props.ConstMessages;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BuyRequest {
	@NotNull(message = ConstMessages.BLANK_USER_ID)
	private Long userId;
	@NotNull(message = ConstMessages.BLANK_PET_ID)
	private Long petId;
	
}
