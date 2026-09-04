package com.petmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmanager.dto.OwnerDTO;
import com.petmanager.service.OwnerService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping (value = "/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Integer> saveOwner(OwnerDTO ownerDTO){
        Integer id =  ownerService.saveOwner(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping 
    public ResponseEntity<Void> updateOwner(int ownerId, OwnerDTO ownerDTO){
        try {
            ownerService.updateOwner(ownerId, ownerDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping 
    public ResponseEntity<Void> updatePetDetails(int ownerId, String petName){
        try {
            ownerService.updatePetDetails(ownerId, petName);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    

    
}