package com.epam.bench.geolocation.service;

import com.epam.bench.geolocation.common.errorhandling.BusinessException;

public class LocationDoesNotExistException extends BusinessException {

    public LocationDoesNotExistException(String message) {
        super(message);
    }
    
    public LocationDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
