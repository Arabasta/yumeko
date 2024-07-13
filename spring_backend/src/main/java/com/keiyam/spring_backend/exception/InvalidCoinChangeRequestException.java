package com.keiyam.spring_backend.exception;

public class InvalidCoinChangeRequestException extends RuntimeException{
    public InvalidCoinChangeRequestException(String message) {
        super(message);
    }
}
