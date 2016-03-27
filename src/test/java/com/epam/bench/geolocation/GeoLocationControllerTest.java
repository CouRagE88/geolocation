package com.epam.bench.geolocation;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.epam.bench.geolocation.controller.IpSearchController;
import com.epam.bench.geolocation.domain.GeoLocationEntity;
import com.epam.bench.geolocation.service.GeoLocationService;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.View;

@ContextConfiguration("classpath:conf/spring/spring-servlet-test.xml")
//@ContextConfiguration(locations = {"classpath:conf/spring/spring-config.xml", "classpath:conf/spring/spring-servlet-test.xml"})
public class GeoLocationControllerTest extends AbstractJUnit4SpringContextTests {
   
    //@Autowired
    //WebApplicationContext wac;
    
    private IpSearchController controller;
    
    @Mock
    private GeoLocationService geoLocationServiceMock;
    
    @Mock
    View mockView;
    
    MockMvc mockMvc;
    
    @Before
    public void setUp() {
        geoLocationServiceMock = EasyMock.createMock(GeoLocationService.class);
        
        controller = new IpSearchController();
        controller.setService(geoLocationServiceMock);
             
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(mockView).build();
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    @After
    public void terDown() {
        geoLocationServiceMock = null;
        controller = null;
    }
    
    /**
     * This test method is successful if the correct view is returned after a successful IP-Search 
     * @throws Exception
     */
    @Test
    public void ProcessSearchTest() throws Exception {
        
        try {
            GeoLocationEntity geoLocation = CreateSampleLocation();
            
            expect(geoLocationServiceMock.getGeoLocation(anyObject(String.class))).andReturn(geoLocation);
            replay(geoLocationServiceMock);
            
            String mav = controller.processSearch(geoLocation, new ModelMap());
            
            verify(geoLocationServiceMock);
            
            assertTrue("ipsearch-result".equals(mav));
            
        } catch (Exception e) {
            fail("Should have thrown an exception.");
        }
    }
    
    //TODO test the various exceptions
    
    //@Test
    //TODO
    public void UnderImplementation() throws Exception {
        
        GeoLocationEntity expectedLocation = CreateSampleLocation();
        
        expect(geoLocationServiceMock.getGeoLocation("223.255.255.0")).andReturn(expectedLocation);      
        replay(geoLocationServiceMock);
        
        this.mockMvc.perform(post("/geolocation/ipsearch-form"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("geoLocation", expectedLocation))
            .andExpect(view().name("/geolocation/ipsearch-result"));
        
        verify(geoLocationServiceMock);
    }
    
    //country: AUSTRALIA; region: AU; city: SOUTH BRISBANE; latitude: -27.483330; longitude: 153.016663; zipCode: 4101; timeZone: GMT+10:00
    protected GeoLocationEntity CreateSampleLocation() {
        GeoLocationEntity location = new GeoLocationEntity();
        location.setIpAddress("223.255.255.0");
        location.setCountryName("Australia");
        location.setRegionName("AU");
        location.setCityName("SOUTH BRISBANE");
        location.setLatitude(-27.483330f);
        location.setLongitude(153.016663f);
        location.setZipCode("4101");
        location.setTimeZone("+10:00");
        
        return location;
    }
}