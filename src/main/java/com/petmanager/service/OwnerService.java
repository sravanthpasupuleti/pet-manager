package com.petmanager.service;

import java.util.List;

import com.petmanager.dto.OwnerDTO;
import com.petmanager.exception.OwnerNotFoundException;

public interface OwnerService {
    Integer saveOwner(OwnerDTO ownerDTO);

	void updateOwner(int ownerId, OwnerDTO ownerDTO) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwners();
}
