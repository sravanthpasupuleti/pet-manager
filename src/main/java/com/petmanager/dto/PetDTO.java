package com.petmanager.dto;

import com.petmanager.enums.Gender;
import com.petmanager.enums.PetType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter 
public abstract class PetDTO {
    @EqualsAndHashCode.Include
	private int id;
	private String name;
	private Gender gender;
	private PetType type;
	private OwnerDTO ownerDTO;
}