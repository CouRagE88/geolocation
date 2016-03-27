package com.epam.bench.geolocation.domain;

import java.util.Objects;
import java.util.TimeZone;

/**
 * Model object to represent geographical location information.
 *
 * @author David_Keri
 */
public class GeoLocation {
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
        if (timeZone == null) {
            return "n/a";
        }
        return timeZone.getID();
    }

    /**
     * Java TZ format: GMT+10:00
     *
     * @param dbTimeZoneFormat the format used in the DB: +10:00
     */
    public void setTimeZone(String dbTimeZoneFormat) {

        this.timeZone = "".equals(dbTimeZoneFormat) || "-".equals(dbTimeZoneFormat)
                        ? TimeZone.getTimeZone(dbTimeZoneFormat)
                        : TimeZone.getTimeZone("GMT" + dbTimeZoneFormat.charAt(0) + dbTimeZoneFormat.substring(1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeoLocation that = (GeoLocation) o;
        return Float.compare(that.latitude, latitude) == 0 &&
            Float.compare(that.longitude, longitude) == 0 &&
            Objects.equals(ipAddress, that.ipAddress) &&
            Objects.equals(countryCode, that.countryCode) &&
            Objects.equals(countryName, that.countryName) &&
            Objects.equals(regionName, that.regionName) &&
            Objects.equals(cityName, that.cityName) &&
            Objects.equals(zipCode, that.zipCode) &&
            Objects.equals(timeZone, that.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, countryCode, countryName, regionName, cityName, latitude, longitude, zipCode, timeZone);
    }

    @Override
    public String toString() {
        return String.format("country: %s; region: %s; city: %s; latitude: %f; longitude: %f; zipCode: %s; timeZone: %s",
                             countryName, regionName, cityName, latitude, longitude, zipCode, getTimeZone());
    }
}
