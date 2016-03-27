package com.epam.bench.geolocation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.epam.bench.geolocation.common.errorhandling.GenericException;
import com.epam.bench.geolocation.domain.GeoLocation;
import com.epam.bench.geolocation.service.GeoLocationService;
import com.epam.bench.geolocation.service.InvalidIpAddressException;
import com.epam.bench.geolocation.service.LocationDoesNotExistException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:conf/spring/spring-config.xml")
//public class GeoLocationServiceTest extends AbstractJUnit4SpringContextTests {
public class GeoLocationServiceTest {

    @Autowired
    private GeoLocationService geoLocationService;

    @Test
    public void getGetLocationForIpAddress() throws GenericException {
        GeoLocation geoLocation = geoLocationService.getGeoLocation("223.255.255.0");
        assertNotNull(geoLocation);
        assertNotNull(geoLocation.getCountryCode());
        assertNotNull(geoLocation.getCountryName());
        assertNotNull(geoLocation.getRegionName());
        assertNotNull(geoLocation.getCityName());
        assertNotNull(geoLocation.getLatitude());
        assertNotNull(geoLocation.getLongitude());
        assertNotNull(geoLocation.getZipCode());
        assertNotNull(geoLocation.getTimeZone());

        //logger.info(geoLocation.toString());
    }

    @Test
    public void getNonExistingGetLocationForIpAddress() throws GenericException {

        try {
            geoLocationService.getGeoLocation("0.255.255.255");
            fail("Should have thrown LocationDoesNotExistException");
        } catch (LocationDoesNotExistException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getGetLocationForTooShortIpAddress() throws GenericException {

        try {
            geoLocationService.getGeoLocation("255.255.0");
            fail("Should have thrown InvalidIpAddressException");
        } catch (InvalidIpAddressException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getGetLocationForTooLongIpAddress() throws GenericException {
        try {
            geoLocationService.getGeoLocation("192.168.2.1.0");
            fail("Should have thrown InvalidIpAddressException");
        } catch (InvalidIpAddressException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getGetLocationForIpAddressWithText() throws GenericException {
        try {
            geoLocationService.getGeoLocation("192.2a5.255.0");
            fail("Should have thrown InvalidIpAddressException");
        } catch (InvalidIpAddressException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getGetLocationForTooLargeIpAddress() throws GenericException {
        try {
            geoLocationService.getGeoLocation("192.168.600.0");
            fail("Should have thrown InvalidIpAddressException");
        } catch (InvalidIpAddressException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getGetLocationForBarbarianIpAddress() throws GenericException {
        try {
            geoLocationService.getGeoLocation(
                "99999999999999999999999999999999999999999999999." +
                    "99999999999999999999999999999999999999999999999." +
                    "99999999999999999999999999999999999999999999999." +
                    "99999999999999999999999999999999999999999999999");

            fail("Should have thrown InvalidIpAddressException");
        } catch (InvalidIpAddressException e) {
            assertTrue(true);
        }
    }

    //@Test
    // 3758096128 = 223.255.255.0
    //TODO
    //    public void getGetLocationReturnsNull() throws GenericException {
    //
    //        JdbcTemplate jdbcTemplateMock = EasyMock.createMock(JdbcTemplate.class);
    //
    //        GeoLocationDao dao = new JdbcGeoLocationDaoImpl(jdbcTemplateMock);
    //
    //        EasyMock.expect(jdbcTemplateMock.queryForObject(EasyMock.anyObject(String.class), EasyMock.anyObject(RowMapper.class))).andReturn(null);
    //        EasyMock.replay(jdbcTemplateMock);
    //
    //        dao.getGeoLocation(3758096128l);
    //
    //        EasyMock.verify(jdbcTemplateMock);
    //    }
}