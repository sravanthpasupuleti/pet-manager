package com.petmanager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petmanager.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer>{

    @Query ("SELECT o FROM Owner o JOIN FETCH o.pet")
	List<Owner> findAll();

    @Query ("SELECT obj FROM Owner obj JOIN FETCH obj.pet WHERE obj.pet.birthDate BETWEEN :startDate AND :endDate")
    List<Owner> findByPet_DomesticPet_BirthDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT obj.id, obj.firstName, obj.lastName, obj.pet.name FROM Owner obj JOIN obj.pet")
    Page<Object[]> findIdAndFirstNameAndLastNameAndPetNamePage(Pageable pageable);
}
