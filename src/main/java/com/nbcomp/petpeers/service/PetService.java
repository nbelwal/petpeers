package com.nbcomp.petpeers.service;

import java.util.List;

import com.nbcomp.petpeers.dto.BuyRequest;
import com.nbcomp.petpeers.dto.PetDto;

public interface PetService {
	List<PetDto> getAllPets();
	List<PetDto> getMyPets(Integer userId);
	PetDto savePet(PetDto petDto);
	PetDto buyPet(BuyRequest buyRequest);
}
