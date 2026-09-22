package com.petmanager.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.petmanager.dto.OwnerPetInfoDTO;
import com.petmanager.dto.UpdatePetDetails;
import com.petmanager.exception.OwnerNotFoundException;
import com.petmanager.exception.ValidationException;
import com.petmanager.service.OwnerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;

@CrossOrigin 
@RestController 
@RequiredArgsConstructor 
@RequestMapping (value = "/owners")
@Validated 
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Integer> saveOwner(@Valid @RequestBody OwnerDTO ownerDTO){
        Integer id =  ownerService.saveOwner(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping ("/{ownerId}")
    public ResponseEntity<OwnerDTO> findOwner(@PathVariable @Min(value = 1, message = "{owner.id.invalid}") int ownerId) throws OwnerNotFoundException{
        return ResponseEntity.status(HttpStatus.FOUND).body(ownerService.findOwner(ownerId));
    }

    @PutMapping ("/{ownerId}")
    public ResponseEntity<?> updateOwner(@PathVariable @Min(value = 1, message = "{owner.id.invalid}") int ownerId, @Valid  @RequestBody OwnerDTO ownerDTO) throws OwnerNotFoundException{
        ownerService.updateOwner(ownerId, ownerDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{ownerId}")
    public ResponseEntity<?> updatePetDetails(@PathVariable @Min(value = 1, message = "{owner.id.invalid}") int ownerId,@Valid @RequestBody UpdatePetDetails updatePetDetails) throws OwnerNotFoundException{
        ownerService.updatePetDetails(ownerId, updatePetDetails.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping ("/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable @Min(value = 1, message = "{owner.id.invalid}") int ownerId) throws OwnerNotFoundException{
        ownerService.deleteOwner(ownerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping ("/pet/dob")
    public ResponseEntity<List<OwnerDTO>> findOwnerBetweenPetDOB(@RequestParam @NotNull  @PastOrPresent(message = "{owner.pet.startDate}")  LocalDate startDate,
                                                    @RequestParam @NotNull  @PastOrPresent(message = "{owner.pet.endDate}")  LocalDate endDate) throws ValidationException{
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwnersByPetDateOfBirthBetween(startDate, endDate));
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwners());
    }

    @GetMapping(params = "version=2")
    public ResponseEntity<List<OwnerDTO>> findAllOwnersWithoutPet(){
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwnersWithoutPet());
    }

    @GetMapping ("/details")
    public ResponseEntity<Page<OwnerPetInfoDTO>> findOwnerPetInfoDTOAsPage(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findOwnerDetailsAsPage(pageable));
    }
    
}