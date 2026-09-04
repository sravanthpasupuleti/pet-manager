package com.petmanager.dto;

import com.petmanager.enums.Gender;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter 
@Getter 
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OwnerDTO {
    @EqualsAndHashCode .Include
    private int id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private String city;
	private String state;
	@EqualsAndHashCode.Include
	private String mobileNumber;
	@EqualsAndHashCode.Include
	private String emailId;
	private PetDTO petDTO;

}
