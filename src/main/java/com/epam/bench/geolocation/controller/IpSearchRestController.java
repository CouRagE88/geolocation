package com.epam.bench.geolocation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.bench.geolocation.controller.model.SearchResult;
import com.epam.bench.geolocation.domain.GeoLocationEntity;
import com.epam.bench.geolocation.domain.IpAddress;
import com.epam.bench.geolocation.service.GeoLocationService;

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
    @RequestMapping(value="add.json", method= RequestMethod.POST)
    public @ResponseBody SearchResult add(@RequestBody IpAddress request) {

        SearchResult response = new SearchResult();
        response.setStatus(SearchResult.Status.EXECUTION_FAILURE);

        try {
            GeoLocationEntity geoLocation = service.getGeoLocation(request.getIpAddress());
            
            response.setCityName(geoLocation.getCityName());
            response.setStatus(SearchResult.Status.OK);

        } catch (Exception e) {
            logger.error("Unable to perform operation ...", e);
        }

        return response;
    }
}

