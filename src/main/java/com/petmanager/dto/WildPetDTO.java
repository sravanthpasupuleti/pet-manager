package com.petmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter 
@Getter 
@ToString (callSuper = true)
public class WildPetDTO extends PetDTO{
    @NotBlank(message = "{pet.birthplace.required}")
    @Size(max = 100, message = "{pet.birthplace.max")
    private String birthPlace;
}
