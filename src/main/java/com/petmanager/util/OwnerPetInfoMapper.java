package com.petmanager.util;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.petmanager.dto.OwnerPetInfoDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface  OwnerPetInfoMapper {
    String invalid_array = "Invalid object array provided.";

    default OwnerPetInfoDTO mapObjectArrayToOwnerPetInfoDTO(Object[] array){
        if (Objects.isNull(array) || array.length < 4) {
            throw new IllegalArgumentException(invalid_array);
        }else{
            return OwnerPetInfoDTO.builder()
            .id((int) array[0])
            .firstName((String) array[1])
            .lastName((String) array[2])
            .petName((String) array[3])
            .build();
        }
    }
}
