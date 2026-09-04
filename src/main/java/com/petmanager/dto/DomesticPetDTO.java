package com.petmanager.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter 
@Getter 
@ToString (callSuper = true)
public class DomesticPetDTO extends PetDTO{
    private LocalDate birthDate;
}
