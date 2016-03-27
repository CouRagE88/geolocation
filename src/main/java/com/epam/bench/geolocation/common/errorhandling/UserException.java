package com.epam.bench.geolocation.common.errorhandling;

/**
 * Exception to handle user errors.
 */
public class UserException extends GenericException {

    public UserException(String message) {
        super(message);
    }
    
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

}
