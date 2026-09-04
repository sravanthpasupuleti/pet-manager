package com.petmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.petmanager.dto.OwnerDTO;
import com.petmanager.entity.Owner;
import com.petmanager.exception.OwnerNotFoundException;
import com.petmanager.repository.OwnerRepository;
import com.petmanager.service.OwnerService;
import com.petmanager.util.OwnerMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService{

    private final OwnerRepository ownerRepository;
	private final OwnerMapper ownerMapper;

    @Value("${owner.not.found}")
    private String ownerNotFound;

	public Integer saveOwner(OwnerDTO ownerDTO){
		Owner owner = ownerMapper.ownerDTOToOwner(ownerDTO);
		ownerRepository.save(owner);
		return owner.getId();
	}

	public void updateOwner(int ownerId, OwnerDTO ownerDTO) throws OwnerNotFoundException{
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
		ownerMapper.customUpdateOwnerFromDTO(ownerDTO, owner);
		ownerRepository.save(owner);
	}

	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException{
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
		owner.getPet().setName(petName);
		ownerRepository.save(owner);
	}

	public List<OwnerDTO> findAllOwners(){
		return ownerRepository.findAll().stream().map(ownerMapper::ownerToOwnerDTO).toList();
	}

}