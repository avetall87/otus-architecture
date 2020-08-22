package ru.spb.avetall.hwarch.service.exception;

public class UserOrIdIsNullException extends IllegalArgumentException {
    
    public UserOrIdIsNullException(String message) {
        super(message);
    }
    
}
