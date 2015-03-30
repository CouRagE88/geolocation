package com.epam.bench.geolocation.dao;

import com.epam.bench.geolocation.domain.GeoLocationEntity;

public interface GeoLocationDao {
       
    GeoLocationEntity getGeoLocation(long ipNumber);
}
