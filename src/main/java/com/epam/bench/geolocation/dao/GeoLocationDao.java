package com.epam.bench.geolocation.dao;

import com.epam.bench.geolocation.domain.GeoLocation;

/**
 * DAO class to retrieve geoLocation information based in input IP number.
 *
 * @author David Keri
 */
public interface GeoLocationDao {

    GeoLocation getGeoLocation(long ipNumber);
}
