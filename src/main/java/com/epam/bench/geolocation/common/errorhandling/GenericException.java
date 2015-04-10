package com.epam.bench.geolocation.common.errorhandling;

/** This Generic Exception class serves as a base of the application's custom exception structure. */
public class GenericException extends Exception {
    
    public GenericException(String message) {
        super(message);
    }
    
    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
