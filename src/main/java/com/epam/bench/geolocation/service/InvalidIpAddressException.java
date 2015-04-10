package com.epam.bench.geolocation.service;

import com.epam.bench.geolocation.common.errorhandling.UserException;

public class InvalidIpAddressException extends UserException {

    public InvalidIpAddressException(String message) {
        super(message);
    }
    
    public InvalidIpAddressException(String message, Throwable cause) {
        super(message, cause);
    }

}
