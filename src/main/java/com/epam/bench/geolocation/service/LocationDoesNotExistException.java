package com.epam.bench.geolocation.service;

import com.epam.bench.geolocation.common.errorhandling.BusinessException;

/**
 * Exception class to handle non-existent location errors.
 *
 * @author David_Keri
 */
public class LocationDoesNotExistException extends BusinessException {

    public LocationDoesNotExistException(String message) {
        super(message);
    }
    
    public LocationDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
