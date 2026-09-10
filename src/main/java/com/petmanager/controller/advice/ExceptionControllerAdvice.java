package com.petmanager.controller.advice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.petmanager.dto.ExceptionDTO;
import com.petmanager.exception.OwnerNotFoundException;
import com.petmanager.exception.ValidationException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice 
public class ExceptionControllerAdvice {

    //Custum defined exceptions
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


    //Built in Exceptions
    @ExceptionHandler 
    public ResponseEntity<List<ExceptionDTO>> handleException(ConstraintViolationException exception){
        List<ExceptionDTO> exceptionDTO = exception.getConstraintViolations()
        .stream()
        .map(except -> 
                ExceptionDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST)
                .message(except.getMessage())
                .timeStamp(LocalDateTime.now())
                .build()
        )
        .toList();
        return ResponseEntity.status(exceptionDTO.get(0).getError()).body(exceptionDTO);
    }

    @ExceptionHandler 
    public ResponseEntity<List<ExceptionDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ExceptionDTO> exceptionDTOs = exception.getAllErrors()
        .stream()
        .map(excep -> ExceptionDTO.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .error(HttpStatus.BAD_REQUEST)
        .message(excep.getDefaultMessage())
        .timeStamp(LocalDateTime.now())
        .build()
        )
        .toList();
        return ResponseEntity.status(exceptionDTOs.get(0).getError()).body(exceptionDTOs);
    }

    @ExceptionHandler
	public ResponseEntity<ExceptionDTO> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException exception) {
		ExceptionDTO errorDTO = ExceptionDTO.builder()
				.message(exception.getMessage())
				.error(HttpStatus.METHOD_NOT_ALLOWED)
				.status(HttpStatus.METHOD_NOT_ALLOWED.value())
				.timeStamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}

    @ExceptionHandler
	public ResponseEntity<ExceptionDTO> handleGenericException(Exception exception) {
		ExceptionDTO errorDTO = ExceptionDTO.builder()
				.message(exception.getMessage())
				.error(HttpStatus.INTERNAL_SERVER_ERROR)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timeStamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}

}
