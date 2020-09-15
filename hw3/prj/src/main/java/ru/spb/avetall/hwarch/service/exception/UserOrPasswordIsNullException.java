package ru.spb.avetall.hwarch.service.exception;

public class UserOrPasswordIsNullException extends IllegalArgumentException {
    
    public UserOrPasswordIsNullException(String message) {
        super(message);
    }
    
}
