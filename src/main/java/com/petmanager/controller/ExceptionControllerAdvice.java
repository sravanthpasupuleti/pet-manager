package com.petmanager.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.petmanager.dto.ExceptionDTO;
import com.petmanager.exception.OwnerNotFoundException;
import com.petmanager.exception.ValidationException;

@RestControllerAdvice 
public class ExceptionControllerAdvice {

    @ExceptionHandler 
    public ResponseEntity<ExceptionDTO> handleOwnerNotFoundException(OwnerNotFoundException exception){
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
        .error(HttpStatus.NOT_FOUND)
        .status(HttpStatus.NOT_FOUND.value())
        .message(exception.getMessage())
        .timeStamp(LocalDateTime.now())
        .build();
        return ResponseEntity.status(exceptionDTO.getStatus()).body(exceptionDTO);
    }

    @ExceptionHandler 
    public ResponseEntity<ExceptionDTO> handleValidationException(ValidationException exception){
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
        .status(HttpStatus.CONFLICT.value())
        .error(HttpStatus.CONFLICT)
        .message(exception.getMessage())
        .timeStamp(LocalDateTime.now())
        .build();
        return ResponseEntity.status(exceptionDTO.getStatus()).body(exceptionDTO);
    }

    @ExceptionHandler 
    public ResponseEntity<ExceptionDTO> handleException(Exception exception){
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .error(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(exception.getMessage())
        .timeStamp(LocalDateTime.now())
        .build();
        return ResponseEntity.status(exceptionDTO.getError()).body(exceptionDTO);
    }
}
