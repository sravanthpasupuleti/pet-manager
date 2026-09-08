package com.petmanager.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
@Setter 
@Getter 
public class ExceptionDTO {
    private Integer status;          //code
    private HttpStatus error;   //msg like NOT_FOUND, CONFLICT
    private String message;
    private LocalDateTime timeStamp;
}
