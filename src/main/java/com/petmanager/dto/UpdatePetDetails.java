package com.petmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 
public class UpdatePetDetails {
    @NotBlank(message = "{name.required}")
    @Size (max = 255, message = "{name.max.length}")
    private String name;
}
