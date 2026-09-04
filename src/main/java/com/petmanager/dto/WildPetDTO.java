package com.petmanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter 
@Getter 
@ToString (callSuper = true)
public class WildPetDTO extends PetDTO{
    private String birthPlace;
}
