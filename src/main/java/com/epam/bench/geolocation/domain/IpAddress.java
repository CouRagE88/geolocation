package com.epam.bench.geolocation.domain;

import java.io.Serializable;

/**
 * Domain object to represent an IP Address.
 */
public class IpAddress implements Serializable {
    private static final long serialVersionUID = 1232840935905083149L;

    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }
}
