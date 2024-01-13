package com.nbcomp.petpeers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nbcomp.petpeers.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findUserByUserName(String userName);
}
