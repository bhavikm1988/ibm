package com.ibm.geolocation.service;

import com.ibm.geolocation.models.IpInfo;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class IPService {

    private static final String REST_URI = "http://ip-api.com/json";

    private Client client = ClientBuilder.newClient();

    public IpInfo getIpInformation(String ip) {
        return client
                .target(REST_URI)
                .path(ip)
                .queryParam("fields", "country,city")
                .request(MediaType.APPLICATION_JSON)
                .get(IpInfo.class);
    }
}
