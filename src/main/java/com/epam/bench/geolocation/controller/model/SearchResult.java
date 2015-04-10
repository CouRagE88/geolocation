package com.epam.bench.geolocation.controller.model;


/**
 * Represents the result of a geographical search with a status code.
 *
 * Used at controller level to represent a JSON object for the REST response body.
 */
public class SearchResult {

    public enum Status {
        OK,
        BUSINESS_EXCEPTION,
        USER_EXCEPTION,
        UNCATEGORIZED_EXCEPTION,
        LOCATION_DOES_NOT_EXIST_EXCEPTION,
        INVALID_IPADDRESS_EXCEPTION}

    private String cityName;
    private Status status;

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String getCityName() {
        return cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
}