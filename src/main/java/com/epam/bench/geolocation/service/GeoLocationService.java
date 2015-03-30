package com.epam.bench.geolocation.service;

import org.springframework.transaction.annotation.Transactional;

import com.epam.bench.geolocation.common.errorhandling.GenericException;
import com.epam.bench.geolocation.domain.GeoLocationEntity;

@Transactional(readOnly=true)
public interface GeoLocationService {
    
    /**
     * Returns the location, based on the input IP-Address.
     * Validates the passed ipAddress and rejects it if it's incomplete, inconsistent etc.
     * @return The location information based on the ipAddress 
     * @param request represents the details of the reservation request
     * @throws GenericException
     */
    GeoLocationEntity getGeoLocation(String ipAddress) throws GenericException;
}

