package com.petmanager.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter 
@Getter 
@ToString (callSuper = true)
public class DomesticPetDTO extends PetDTO{
    @NotNull(message = "{pet.dob.required}")
    @PastOrPresent(message = "{pet.dob.past}")
    private LocalDate birthDate;
}
