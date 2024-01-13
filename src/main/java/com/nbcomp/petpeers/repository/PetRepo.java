package com.nbcomp.petpeers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nbcomp.petpeers.entity.Pet;

public interface PetRepo extends JpaRepository<Pet, Long>{

}
