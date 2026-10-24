package com.petmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.petmanager.dto.PetStatisticsDTO;
import com.petmanager.service.PetService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class PetController {

    private final PetService petService;

    public ResponseEntity<PetStatisticsDTO> getStatistics(){
        return ResponseEntity.status(HttpStatus.OK).body(petService.getStatistics());
    }
}
