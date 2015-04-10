package com.epam.bench.geolocation.domain;

import java.io.Serializable;

public class IpAddress implements Serializable {

    private static final long serialVersionUID = 1232840935905083149L;
    String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
        
}
