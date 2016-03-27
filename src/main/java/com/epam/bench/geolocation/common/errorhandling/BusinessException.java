package com.epam.bench.geolocation.common.errorhandling;

/**
 * Represents business-related problems, for example certain business conditions are not met.
 */
public class BusinessException extends GenericException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}