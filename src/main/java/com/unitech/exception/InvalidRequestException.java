package com.unitech.exception;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String message) {
        super(message);
    }
}
