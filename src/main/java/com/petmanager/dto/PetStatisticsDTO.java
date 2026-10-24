package com.petmanager.dto;

import java.util.HashMap;
import java.util.Map;

public class PetStatisticsDTO {
    private long total;
	private Map<String, PetCategoryStatisticsDTO> category = new HashMap<>();

	public void incrementTotal(long count) {
		this.total += count;
	}

	public PetCategoryStatisticsDTO getOrCreateCategory(String category) {
		return this.category.computeIfAbsent(category, _ -> new PetCategoryStatisticsDTO());
	}
}
