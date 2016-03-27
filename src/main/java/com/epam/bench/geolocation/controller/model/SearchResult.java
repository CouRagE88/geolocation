package com.epam.bench.geolocation.controller.model;

/**
 * Represents the result of a geographical search with a status code.
 * <p>
 * Used at controller level to represent a JSON object for the REST response body.
 * </p>
 */
public class SearchResult {

    public enum Status {
        OK,
        BUSINESS_EXCEPTION,
        USER_EXCEPTION,
        UNEXPECTED_EXCEPTION,
        LOCATION_DOES_NOT_EXIST_EXCEPTION,
        INVALID_IP_ADDRESS_EXCEPTION
    }

    private String countryName;
    private String cityName;
    private float latitude;
    private float longitude;

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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}