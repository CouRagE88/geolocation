package com.epam.bench.geolocation.controller;

import com.epam.bench.geolocation.common.errorhandling.BusinessException;
import com.epam.bench.geolocation.common.errorhandling.UserException;
import com.epam.bench.geolocation.domain.GeoLocation;
import com.epam.bench.geolocation.service.GeoLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

//@Controller
//@RequestMapping(value = "/ipsearch.html")
public class IpSearchController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    //@Autowired
    private GeoLocationService service;

    /**
     * Unit testing purposes
     * @param service
     */
    public void setService(GeoLocationService service) {
        this.service = service;
    }
    
    /**
     * The initialized method called once the servlet is up deployed
     */
    //@PostConstruct
    public void initialized() {
        logger.debug("IpSearchController is initialized - @PostConstruct called");
    }
    
    /**
     * This method is used to handle all HTTP GET requests.
     * 
     * @return ModelAndView, where ipsearch-form is the target view and a newly
     * created GeoLocationEntity object is the model.
     * The latter is required by the spring form model binding mechanism for some magical reason.
     */
    //@RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayIpSearchForm() {
        return new ModelAndView("ipsearch-form", "geoLocation", new GeoLocation());
    }
    
    /**
     * This method processes the user's search requests and redirects to the result page,
     * or throws an appropriate exception.
     * 
     * @param geoLocation the entity behind the spring form
     * @param model is the object to which the requested location data will be saved
     * @return the target view's name
     * @throws Exception which can be either business-, user- or unknown Exception.
     * Anything thrown here will be handled by the simpleMappingExceptionResolver bean.
     */
    //@RequestMapping(method=RequestMethod.POST)
    public String processSearch(@ModelAttribute("geoLocation") GeoLocation geoLocation,
            //BindingResult formBindingResult,
            ModelMap model) throws Exception {
                
        try {
            
            /*if (formBindingResult.hasErrors()) {
                formBindingResult.reject("error.user");
                logger.debug("validation of form data failed.");
                
            } else {*/
                logger.debug("Starting IP-based location search.");
                
                GeoLocation resultLocation = service.getGeoLocation(geoLocation.getIpAddress());
                
                model.addAttribute("countryName", resultLocation.getCountryName());
                model.addAttribute("cityName", resultLocation.getCityName());
                model.addAttribute("latitude", resultLocation.getLatitude());
                model.addAttribute("longitude", resultLocation.getLongitude());
                
                logger.debug("ipsearch was successful, model data: "+resultLocation.toString());
           // }
            
        } catch (BusinessException businessException) {
            logger.warn("Business exception during processing Reservation request", businessException);
            throw businessException;
            
        } catch (UserException userException) {
            logger.warn("UserException during processing the search IP request", userException);
            throw userException;
            
        } catch (Exception uncategorizedException) {
            logger.error("uncategorized exception during processing the search IP request", uncategorizedException);
            throw uncategorizedException;
        }
        
        return "ipsearch-result";
    }
}
