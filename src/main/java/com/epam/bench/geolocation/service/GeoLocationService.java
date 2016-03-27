package com.epam.bench.geolocation.service;

import org.springframework.transaction.annotation.Transactional;

import com.epam.bench.geolocation.common.errorhandling.GenericException;
import com.epam.bench.geolocation.domain.GeoLocation;

/**
 * Service to retrieve geo location information based on received ip address.
 */
@Transactional(readOnly=true)
public interface GeoLocationService {
    
    /**
     * Returns the location, based on the input IP-Address.
     * Validates the passed ipAddress and rejects it if it's incomplete, inconsistent etc.
     * @return The location information based on the ipAddress
     * @throws GenericException
     */
    GeoLocation getGeoLocation(String ipAddress) throws GenericException;
}

