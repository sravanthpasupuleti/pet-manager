package com.petmanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petmanager.dto.OwnerDTO;
import com.petmanager.dto.OwnerPetInfoDTO;
import com.petmanager.exception.OwnerNotFoundException;
import com.petmanager.exception.ValidationException;

public interface OwnerService {
    Integer saveOwner(OwnerDTO ownerDTO);

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	void updateOwner(int ownerId, OwnerDTO ownerDTO) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwnersByPetDateOfBirthBetween(LocalDate startDate, LocalDate endDate) throws ValidationException;

	List<OwnerDTO> findAllOwners();

	List<OwnerDTO> findAllOwnersWithoutPet();

	Page<OwnerPetInfoDTO> findOwnerDetailsAsPage(Pageable pageable);
}
