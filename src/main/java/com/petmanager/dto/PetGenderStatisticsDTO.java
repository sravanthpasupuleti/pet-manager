package com.petmanager.dto;

import java.util.EnumMap;
import java.util.Map;

import com.petmanager.enums.PetType;

public class PetGenderStatisticsDTO {
    private long total;
	private Map<PetType, Long> type = new EnumMap<>(PetType.class);
	
	public void incrementTotal(long count) {
		this.total += count;
	}
	
	public void mergeOrCreateType(PetType type, long count) {
		this.type.merge(type, count, Long::sum);
	}
}
