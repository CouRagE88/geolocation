package com.epam.bench.geolocation.controller;

import javax.annotation.PostConstruct;

import com.epam.bench.geolocation.common.errorhandling.BusinessException;
import com.epam.bench.geolocation.common.errorhandling.UserException;
import com.epam.bench.geolocation.controller.model.SearchResult;
import com.epam.bench.geolocation.domain.GeoLocation;
import com.epam.bench.geolocation.domain.IpAddress;
import com.epam.bench.geolocation.service.GeoLocationService;
import com.epam.bench.geolocation.service.InvalidIpAddressException;
import com.epam.bench.geolocation.service.LocationDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IpSearchRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpSearchRestController.class);

    @Autowired
    private GeoLocationService service;

    @PostConstruct
    public void initialized() {
        LOGGER.debug("RestEndpointController initialized");
    }

    /**
     * REST request endpoint which accepts parameters as a JSON-serialized object.
     * <p>
     * JSON request is parsed and populated into the appropriate model object due to the @RequestBody annotation.
     * </p>
     * @return the result object, which will be converted to JSON due to the
     */
    @RequestMapping(value = "search.json", method = RequestMethod.POST)
    public @ResponseBody SearchResult add(@RequestBody IpAddress request) throws Exception {
        SearchResult response = new SearchResult();
        response.setStatus(SearchResult.Status.UNEXPECTED_EXCEPTION);
        try {
            LOGGER.debug("Starting IP-based location search.");
            GeoLocation geoLocation = service.getGeoLocation(request.getIpAddress());
            //TODO asserts
            response.setCountryName(geoLocation.getCountryName());
            response.setCityName(geoLocation.getCityName());
            response.setLatitude(geoLocation.getLatitude());
            response.setLongitude(geoLocation.getLongitude());
            response.setStatus(SearchResult.Status.OK);

        } catch (BusinessException businessException) {

            if (businessException instanceof LocationDoesNotExistException) {
                response.setStatus(SearchResult.Status.LOCATION_DOES_NOT_EXIST_EXCEPTION);
            } else {
                response.setStatus(SearchResult.Status.BUSINESS_EXCEPTION);
            }

        } catch (UserException userException) {
            if (userException instanceof InvalidIpAddressException) {
                response.setStatus(SearchResult.Status.INVALID_IP_ADDRESS_EXCEPTION);
            } else {
                response.setStatus(SearchResult.Status.USER_EXCEPTION);
            }

        } catch (Exception unexpectedException) {
            response.setStatus(SearchResult.Status.UNEXPECTED_EXCEPTION);
            throw new BusinessException("Unexpected exception during processing the search IP request", unexpectedException);
        }
        return response;
    }
}
