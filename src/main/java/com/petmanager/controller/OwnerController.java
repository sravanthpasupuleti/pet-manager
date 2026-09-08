package com.petmanager.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petmanager.dto.OwnerDTO;
import com.petmanager.dto.UpdatePetDetails;
import com.petmanager.exception.OwnerNotFoundException;
import com.petmanager.exception.ValidationException;
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

    @GetMapping ("/{ownerId}")
    public ResponseEntity<OwnerDTO> findOwner(@PathVariable int ownerId) throws OwnerNotFoundException{
        return ResponseEntity.status(HttpStatus.FOUND).body(ownerService.findOwner(ownerId));
    }

    @PutMapping ("/{ownerId}")
    public ResponseEntity<?> updateOwner(@PathVariable int ownerId, @RequestBody OwnerDTO ownerDTO) throws OwnerNotFoundException{
        ownerService.updateOwner(ownerId, ownerDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{ownerId}")
    public ResponseEntity<?> updatePetDetails(@PathVariable int ownerId, @RequestBody UpdatePetDetails updatePetDetails) throws OwnerNotFoundException{
        ownerService.updatePetDetails(ownerId, updatePetDetails.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping ("/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable int ownerId) throws OwnerNotFoundException{
        ownerService.deleteOwner(ownerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping ("/pet/dob")
    public ResponseEntity<List<OwnerDTO>> findOwnerBetweenPetDOB(@RequestParam(required = false) LocalDate startDate,
                                                    @RequestParam(required = false) LocalDate endDate) throws ValidationException{
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwnersByPetDateOfBirthBetween(startDate, endDate));
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwners());
    }
    
}