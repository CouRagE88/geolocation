package com.epam.bench.geolocation.domain;

import java.util.TimeZone;

/**
 * Model object to represent geographical location information.
 * @author David_Keri
 *
 */
public class GeoLocationEntity {

    private String ipAddress;
    private String countryCode;
    private String countryName;
    private String regionName;
    private String cityName;
    private float latitude;
    private float longitude;
    private String zipCode;
    private TimeZone timeZone;
    
    public String getIpAddress() {
        return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public String getCountryCode() {
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getCountryName() {
        return countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    public String getRegionName() {
        return regionName;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
    public String getCityName() {
        return cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
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
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getTimeZone() {
        return timeZone.getID();
    }
    
    /** 
     * Java TZ format: GMT+10:00
     * @param dbTimeZoneFormat the format used in the DB: +10:00
     */
    public void setTimeZone(String dbTimeZoneFormat) {
        
        if("".equals(dbTimeZoneFormat) || "-".equals(dbTimeZoneFormat)) {
            this.timeZone = TimeZone.getTimeZone(dbTimeZoneFormat);
            
        } else {
            StringBuilder tzBuilder = new StringBuilder();
            tzBuilder.append("GMT").append(dbTimeZoneFormat.charAt(0)).append(dbTimeZoneFormat.substring(1));
            this.timeZone = TimeZone.getTimeZone(tzBuilder.toString());
        }
    }
    
    @Override
    public String toString() {
        return String.format("country: %s; region: %s; city: %s; latitude: %f; longitude: %f; zipCode: %s; timeZone: %s",
                countryName, regionName, cityName, latitude, longitude, zipCode, timeZone.getID());     
    }
}
