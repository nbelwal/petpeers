package com.nbcomp.petpeers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nbcomp.petpeers.dto.BuyRequest;
import com.nbcomp.petpeers.dto.PetDto;
import com.nbcomp.petpeers.entity.Pet;
import com.nbcomp.petpeers.entity.User;
import com.nbcomp.petpeers.exception.PetAlreadySoldException;
import com.nbcomp.petpeers.exception.ResourceNotFoundException;
import com.nbcomp.petpeers.props.ConstMessages;
import com.nbcomp.petpeers.repository.PetRepo;
import com.nbcomp.petpeers.repository.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{

	private final PetRepo petRepo;
	 
	private final UserRepo userRepo;
 
	@Override
	@Transactional
	public List<PetDto> getAllPets() {
		List<PetDto> petDtoList = new ArrayList<>();
		List<Pet> pets = petRepo.findAll();
		pets.forEach(pet -> petDtoList.add(petToPetDto(pet)));
		return petDtoList;
	}
 
	@Override
	public List<PetDto> getMyPets(Integer userId) {
 
		List<PetDto> petDtoList = new ArrayList<>();
 
		User user = userRepo.findById(Long.valueOf(userId))
				.orElseThrow(() -> new ResourceNotFoundException(ConstMessages.RESOURCE_NOT_FOUND,userId.toString()));
 
		user.getPets().forEach(pet -> petDtoList.add(petToPetDto(pet)));
		return petDtoList;
 
	}
 
	@Override
	public PetDto savePet(PetDto petDto) {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDto, pet);
		Pet savedPet = petRepo.save(pet);
		return petToPetDto(savedPet);
	}
 
	@Override
	public PetDto buyPet(BuyRequest buyRequest) {
		Pet pet = petRepo.findById(buyRequest.getPetId())
				.orElseThrow(() -> new ResourceNotFoundException(ConstMessages.RESOURCE_NOT_FOUND,buyRequest.getPetId().toString()));
		
		if(pet.getUser()!=null)
			throw new PetAlreadySoldException(ConstMessages.ALREADY_SOLD);
		
		User user = userRepo.findById(buyRequest.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(ConstMessages.RESOURCE_NOT_FOUND,buyRequest.getUserId().toString()));
 
		pet.setUser(user);
		Pet savedPet = petRepo.save(pet);
		return petToPetDto(savedPet);
	}
 
	// helper methods
 
	private PetDto petToPetDto(Pet pet) {
		PetDto petDto = new PetDto();
		BeanUtils.copyProperties(pet, petDto);
		return petDto;
	}
	
}
