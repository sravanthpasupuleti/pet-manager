package com.petmanager.dto;

import com.petmanager.enums.Gender;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

	@NotBlank(message = "{name.required}")
    @Size (max = 100, message = "{name.max.length}")
	private String firstName;

	@NotBlank(message = "{name.required}")
    @Size (max = 100, message = "{name.max.length}")
	private String lastName;

	@NotNull(message = "{owner.gender.required}")
	private Gender gender;

	@NotBlank(message = "{owner.city.required}")
    @Size (max = 100, message = "{city.max.length}")
	private String city;

	@NotBlank(message = "{owner.state.required}")
    @Size (max = 100, message = "{state.max.length}")
	private String state;
	@EqualsAndHashCode.Include

	@NotBlank(message = "{owner.mobile.number.required}")
	@Size (min = 10, max = 13, message = "{mobilenumber.max.length}")
	private String mobileNumber;

	@Email (message = "{owner.email.invalid}")
	@NotBlank(message = "{owner.email.required}")
	@EqualsAndHashCode.Include
	private String emailId;

	@Valid
	@NotNull(message = "pet.details.required")
	private PetDTO petDTO;

}
