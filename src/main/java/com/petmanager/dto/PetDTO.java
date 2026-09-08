package com.petmanager.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.petmanager.enums.Gender;
import com.petmanager.enums.PetType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes({@Type(value = DomesticPetDTO.class, name = "Domestic"), @Type(value = WildPetDTO.class, name = "Wild")})
@Setter
@Getter
@EqualsAndHashCode 
public abstract class PetDTO {
    @EqualsAndHashCode.Include
	private int id;
	private String name;
	private Gender gender;
	private PetType type;
	private OwnerDTO ownerDTO;
}