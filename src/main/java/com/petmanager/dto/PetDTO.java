package com.petmanager.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.petmanager.enums.Gender;
import com.petmanager.enums.PetType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

	@NotBlank(message = "{name.required}")
    @Size (max = 255, message = "{name.max.length}")
	private String name;

	@NotNull (message = "{pet.gender.required}")
	private Gender gender;

	@NotNull (message = "{pet.type.requires}")
	private PetType type;

	
	private OwnerDTO ownerDTO;
}