package com.epam.bench.geolocation.common.errorhandling;

public class UserException extends GenericException {

    public UserException(String message) {
        super(message);
    }
    
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

}
