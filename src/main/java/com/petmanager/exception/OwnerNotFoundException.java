package com.petmanager.exception;

public class OwnerNotFoundException extends Exception{
    public OwnerNotFoundException(String msg){
        super(msg);
    }
}