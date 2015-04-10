package com.epam.bench.geolocation.controller.model;


/**
 * Represents the result of a mathematical computation with a status code.
 *
 * Used at controller level to represent a JSON object for the REST response body.
 */
public class SearchResult {

    public enum Status {
        OK,
        EXECUTION_FAILURE
    }

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