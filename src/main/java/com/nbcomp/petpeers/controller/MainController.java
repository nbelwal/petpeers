package com.nbcomp.petpeers.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nbcomp.petpeers.dto.BuyRequest;
import com.nbcomp.petpeers.dto.Login;
import com.nbcomp.petpeers.dto.PetDto;
import com.nbcomp.petpeers.dto.UserDto;
import com.nbcomp.petpeers.service.PetService;
import com.nbcomp.petpeers.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainController {
	private final UserService userService;
	 
	private final PetService petService;
 
	// pet api's
 
	@PostMapping("/api/pets")
	public ResponseEntity<PetDto> savePet(@Valid @RequestBody PetDto petDto) {
		PetDto savedPetDto = petService.savePet(petDto);
		return new ResponseEntity<>(savedPetDto, HttpStatus.CREATED);
	}
 
	@PutMapping("/api/buy")
	public ResponseEntity<PetDto> buyPet(@Valid @RequestBody BuyRequest buyRequest) {
		PetDto petDto = petService.buyPet(buyRequest);
		return new ResponseEntity<>(petDto, HttpStatus.CREATED);
	}
 
	@GetMapping("/api/pets/{userId}")
	public ResponseEntity<List<PetDto>> getMyPets(@PathVariable int userId) {
		List<PetDto> myPetList = petService.getMyPets(userId);
		return new ResponseEntity<>(myPetList, HttpStatus.OK);
	}
 
	@GetMapping("/api/pets")
	public ResponseEntity<List<PetDto>> getAllPets() {
		List<PetDto> pets = petService.getAllPets();
		return new ResponseEntity<>(pets, HttpStatus.OK);
	}
 
	// user api's
 
	@PostMapping("/api/users")
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
		UserDto savedUser = userService.saveUser(userDto);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
 
	@PostMapping("/api/login")
	public ResponseEntity<UserDto> authenticateUser(@Valid @RequestBody Login login) {
		UserDto savedUser = userService.authenticateUser(login);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}
	
}
