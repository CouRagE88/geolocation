package com.epam.bench.geolocation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.bench.geolocation.common.errorhandling.BusinessException;
import com.epam.bench.geolocation.common.errorhandling.UserException;
import com.epam.bench.geolocation.controller.model.SearchResult;
import com.epam.bench.geolocation.domain.GeoLocationEntity;
import com.epam.bench.geolocation.domain.IpAddress;
import com.epam.bench.geolocation.service.GeoLocationService;
import com.epam.bench.geolocation.service.InvalidIpAddressException;
import com.epam.bench.geolocation.service.LocationDoesNotExistException;

import javax.annotation.PostConstruct;

@Controller
public class IpSearchRestController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private GeoLocationService service;

    @PostConstruct
    public void initialized() {
        logger.debug("RestEndpointController initialized");
    }

    /**
     * REST request endpoint which accepts parameters as a JSON-serialized object.
     *
     * JSON request is parsed and populated into the appropriate model object due to the @RequestBody annotation.
     *
     *  @return the result object, which will be converted to JSON due to the
     *          @ResponseBody annotation and the Jackson library
     */
    @RequestMapping(value="search.json", method= RequestMethod.POST)
    public @ResponseBody SearchResult add(@RequestBody IpAddress request) throws Exception {

        SearchResult response = new SearchResult();
        response.setStatus(SearchResult.Status.UNCATEGORIZED_EXCEPTION);

        try {
            logger.debug("Starting IP-based location search.");
            
            GeoLocationEntity geoLocation = service.getGeoLocation(request.getIpAddress());
            
            //TODO asserts
            response.setCountryName(geoLocation.getCountryName());
            response.setCityName(geoLocation.getCityName());
            response.setLatitude(geoLocation.getLatitude());
            response.setLongitude(geoLocation.getLongitude());
            response.setStatus(SearchResult.Status.OK);

        } catch (BusinessException businessException) {
            
            if(businessException instanceof LocationDoesNotExistException) {
                response.setStatus(SearchResult.Status.LOCATION_DOES_NOT_EXIST_EXCEPTION);
            }
            else {
                response.setStatus(SearchResult.Status.BUSINESS_EXCEPTION);
            }
            //throw new BusinessException("Business exception during processing Reservation request", businessException);
            
        } catch (UserException userException) {
            if(userException instanceof InvalidIpAddressException) {
                response.setStatus(SearchResult.Status.INVALID_IPADDRESS_EXCEPTION);
            }
            else {
                response.setStatus(SearchResult.Status.USER_EXCEPTION);
            }
            //throw new UserException("UserException during processing the search IP request", userException);
            
        } catch (Exception uncategorizedException) {
            response.setStatus(SearchResult.Status.UNCATEGORIZED_EXCEPTION);
            throw new BusinessException("uncategorized exception during processing the search IP request", uncategorizedException);
        }
        return response;
    }
}

