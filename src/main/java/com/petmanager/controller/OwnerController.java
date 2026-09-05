package com.petmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Integer> saveOwner(@RequestBody OwnerDTO ownerDTO){
        Integer id =  ownerService.saveOwner(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping 
    public ResponseEntity<Void> updateOwner(@RequestBody OwnerDTO ownerDTO){
        try {
            ownerService.updateOwner(ownerDTO.getId(), ownerDTO);
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

    @GetMapping("/all")
    public ResponseEntity<List<OwnerDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwners());
    }
    
}