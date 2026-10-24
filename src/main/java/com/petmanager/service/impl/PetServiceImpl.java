package com.petmanager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petmanager.dto.PetCategoryStatisticsDTO;
import com.petmanager.dto.PetGenderStatisticsDTO;
import com.petmanager.dto.PetStatisticsDTO;
import com.petmanager.enums.Gender;
import com.petmanager.enums.PetType;
import com.petmanager.repository.PetRepository;
import com.petmanager.service.PetService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PetServiceImpl implements PetService{

    private final PetRepository petRepository;

    public PetStatisticsDTO getStatistics(){
        PetStatisticsDTO petStatisticsDTO = new PetStatisticsDTO();
		List<Object[]> rows = petRepository.fetchStatistics();
		for (Object[] row : rows) {
			String category = (String) row[0];
			Gender gender = (Gender) row[1];
			PetType type = (PetType) row[2];
			long count = (Long) row[3];
			petStatisticsDTO.incrementTotal(count);
			PetCategoryStatisticsDTO petCategoryStatisticsDTO = petStatisticsDTO.getOrCreateCategory(category);
			petCategoryStatisticsDTO.incrementTotal(count);
			PetGenderStatisticsDTO petGenderStatisticsDTO = petCategoryStatisticsDTO.getOrCreateGender(gender);
			petGenderStatisticsDTO.incrementTotal(count);
			petGenderStatisticsDTO.mergeOrCreateType(type, count);
		}
		return petStatisticsDTO;
    }
}
