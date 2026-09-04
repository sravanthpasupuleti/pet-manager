package com.petmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petmanager.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer>{
	
}
