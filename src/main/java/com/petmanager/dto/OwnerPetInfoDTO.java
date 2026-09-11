package com.petmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
@Setter 
@Getter 
public class OwnerPetInfoDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String petName;
}
