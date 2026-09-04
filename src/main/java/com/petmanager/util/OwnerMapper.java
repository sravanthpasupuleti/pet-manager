package com.petmanager.util;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.petmanager.dto.DomesticPetDTO;
import com.petmanager.dto.OwnerDTO;
import com.petmanager.dto.PetDTO;
import com.petmanager.dto.WildPetDTO;
import com.petmanager.entity.DomesticPet;
import com.petmanager.entity.Owner;
import com.petmanager.entity.Pet;
import com.petmanager.entity.WildPet;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {

    String UNSUPPORTED_PET_INSTANCE = "Unsupported pet instance: %s";

    @Mapping(target = "pet", source = "petDTO")
    Owner ownerDTOToOwner(OwnerDTO ownerDTO);

    default Pet petDTOToPet(PetDTO petDTO){
        return switch(petDTO){
            case DomesticPetDTO domesticPetDto -> domesticPetDtoToDomesticPet(domesticPetDto);
            case WildPetDTO wildPetDto -> wildPetDtoToWildPet(wildPetDto);
            default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, petDTO.getClass()));
        };
    }

    @Mapping(target = "owner", ignore = true)
    DomesticPet domesticPetDtoToDomesticPet(DomesticPetDTO domesticPetDto);

    @Mapping (target = "owner", ignore = true)
    WildPet wildPetDtoToWildPet(WildPetDTO wildPetDto);





    @Mapping(source = "pet", target = "petDTO")
	OwnerDTO ownerToOwnerDTO(Owner owner);

	default PetDTO petToPetDTO(Pet pet) {
		return switch (pet) {
		case DomesticPet domesticPet -> domesticPetToDomesticPetDTO(domesticPet);
		case WildPet wildPet -> wildPetToWildPetDTO(wildPet);
		default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, pet.getClass()));
		};
	}

	@Mapping(target = "ownerDTO", ignore = true)
	DomesticPetDTO domesticPetToDomesticPetDTO(DomesticPet domesticPet);

	@Mapping(target = "ownerDTO", ignore = true)
	WildPetDTO wildPetToWildPetDTO(WildPet wildPet);



    
    default void customUpdateOwnerFromDTO(OwnerDTO ownerDTO, Owner owner) {
        updateOwnerFromDTO(ownerDTO, owner);
        if (Objects.nonNull(ownerDTO.getPetDTO()) && Objects.nonNull(owner.getPet())) {
            updatePetFromDTO(ownerDTO.getPetDTO(), owner.getPet());
        }
    }

	@Mapping(source = "petDTO", target = "pet", ignore = true)
	void updateOwnerFromDTO(OwnerDTO ownerDTO, @MappingTarget Owner owner);

	default void updatePetFromDTO(PetDTO petDTO, Pet pet) {
		switch (petDTO) {
		case DomesticPetDTO domesticPetDTO -> updateDomesticPetFromDTO(domesticPetDTO, (DomesticPet) pet);
		case WildPetDTO wildPetDTO -> updateWildPetFromDTO(wildPetDTO, (WildPet) pet);
		default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, petDTO.getClass()));
		}
	}

	@Mapping(target = "owner", ignore = true)
	void updateDomesticPetFromDTO(DomesticPetDTO domesticPetDTO, @MappingTarget DomesticPet domesticPet);

	@Mapping(target = "owner", ignore = true)
	void updateWildPetFromDTO(WildPetDTO wildPetDTO, @MappingTarget WildPet wildPet);
}
