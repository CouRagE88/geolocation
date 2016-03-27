package com.epam.bench.geolocation.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.epam.bench.geolocation.dao.GeoLocationDao;
import com.epam.bench.geolocation.domain.GeoLocation;

/**
 * JDBC Implementation if the GeoLocationDao interface
 *
 * @author David_Keri
 */
@Repository
public class JdbcGeoLocationDao implements GeoLocationDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcGeoLocationDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * For Unit Testing purposes
     */
    public JdbcGeoLocationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This method is used to query the database for location-info
     *
     * @param ipNumber is a calculated value based on the user's input IpAddress
     */
    public GeoLocation getGeoLocation(long ipNumber) {

        GeoLocation entity = jdbcTemplate.queryForObject(
            "SELECT country_code, country_name, region_name, city_name, latitude, longitude, zip_code, time_zone" +
                " FROM ip2location_db11 WHERE (ip_from <= ? AND ip_to >= ?)", new GeoLocationEntityRowMapper(), ipNumber, ipNumber);

        Assert.notNull(entity, "The response object GeoLocationEntity must not be null");
        return entity;
    }

    /**
     * Private RowMapper implementation to populate the GeoLocationEntity objects.
     */
    private class GeoLocationEntityRowMapper implements RowMapper<GeoLocation> {

        public GeoLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
            GeoLocation geoLocationEntity = new GeoLocation();

            geoLocationEntity.setCountryCode(rs.getString("country_code"));
            geoLocationEntity.setCountryName(rs.getString("country_name"));
            geoLocationEntity.setRegionName(rs.getString("country_code"));
            geoLocationEntity.setCityName(rs.getString("city_name"));
            geoLocationEntity.setLatitude(rs.getFloat("latitude"));
            geoLocationEntity.setLongitude(rs.getFloat("longitude"));
            geoLocationEntity.setZipCode(rs.getString("zip_code"));
            geoLocationEntity.setTimeZone(rs.getString("time_zone"));

            return geoLocationEntity;
        }
    }
}
