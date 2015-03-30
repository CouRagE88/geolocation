package com.epam.bench.geolocation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.bench.geolocation.common.errorhandling.GenericException;
import com.epam.bench.geolocation.common.errorhandling.UserException;
import com.epam.bench.geolocation.dao.GeoLocationDao;
import com.epam.bench.geolocation.domain.GeoLocationEntity;
import com.epam.bench.geolocation.service.GeoLocationService;
import com.epam.bench.geolocation.service.InvalidIpAddressException;
import com.epam.bench.geolocation.service.LocationDoesNotExistException;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    private static final Logger logger = LoggerFactory.getLogger(GeoLocationServiceImpl.class); 
    
    @Autowired
    private GeoLocationDao geoLocationDao;
    
    public GeoLocationEntity getGeoLocation(String ipAddress) throws GenericException {
        
        logger.debug("ipAddress to be validated & transformed: " + ipAddress);  
        
        validateIpAddress(ipAddress);  
        long ipNumber = convertIpAddressToNumber(ipAddress);
        
        logger.debug("ipAddress to ipNumber: " + ipNumber);
        
        GeoLocationEntity geoLocation = geoLocationDao.getGeoLocation(ipNumber);
                
        validateLocation(geoLocation);
        return geoLocation;
    }
    
    /** This method is validates the submitted IPv4 Address and throws an Exception in case its invalid. 
     * <p> IPv4 Address is valid, if it consists of 4, dot separated numbers, each number being 0 <= number < 255 </p> 
     * @param ipAddress is an IP-Address to be validated
     * @throws UserException
     */
    private void validateIpAddress(String ipAddress) throws UserException {
        
        String[] tokens = ipAddress.split("\\.");
        if (tokens.length != 4) {
            logger.error("Invalid Ip-Address format: "+ipAddress + " - address must consist of 4, dot-separated parts");
            throw new InvalidIpAddressException();
        }
        
        for (String str : tokens) {
            try {
                int i = Integer.parseInt(str);
                if((i < 0) || (i > 255)) {
                    logger.error("Invalid IPv4 Address: "+ipAddress+" -  may only contain digits between 0 and 255.");
                    throw new InvalidIpAddressException();
                }
            } catch (NumberFormatException e) {
                logger.error("Invalid IPv4 Address: "+ipAddress+" - it may only contain digits.");
                throw new InvalidIpAddressException();
            }
        }
    }
    
    /** This method converts a valid IPv4 Address to its IPNumber format.
     * @param ipAddress is a valid IPv4 Address
     */
    private long convertIpAddressToNumber(String ipAddress) {
              
        String[] parts = ipAddress.split("\\.");
        
        return Long.parseLong(parts[0])*16777216 +
                Long.parseLong(parts[1])*65536 + 
                Long.parseLong(parts[2]) * 256 + 
                Long.parseLong(parts[3]);
    }
    
    /**
     * This method validates a geoLocation entity returned from the database.
     * <p> A location is 'non-exiting' if neither of its attributes contain meaningful information. </p>
     * @param geoLocation is existing entity
     * @throws LocationDoesNotExistException
     */
    private void validateLocation(GeoLocationEntity geoLocation) throws LocationDoesNotExistException {
        
        if("".equals(geoLocation.getCountryCode()) || "-".equals(geoLocation.getCountryCode()) &&
           "".equals(geoLocation.getCountryName()) || "-".equals(geoLocation.getCountryName()) &&
           "".equals(geoLocation.getRegionName()) || "-".equals(geoLocation.getRegionName()) &&
           "".equals(geoLocation.getCityName()) || "-".equals(geoLocation.getCityName()) &&
           0 == geoLocation.getLatitude() || 0 == geoLocation.getLatitude() &&
           0 == geoLocation.getLongitude() || 0 == geoLocation.getLongitude() &&
           "".equals(geoLocation.getZipCode()) || "-".equals(geoLocation.getZipCode()) &&
           "".equals(geoLocation.getTimeZone()) || "-".equals(geoLocation.getZipCode()))
        {
            logger.error("There is no matching location in the database for this IPv4 Address.");
            throw new LocationDoesNotExistException();
        }
    }
}
