package com.project.geo.util;

import com.project.geo.dao.GeoLocation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class LocationUtil {

    private static String url = "http://ip-api.com/json/";
    private final Client client;
    public LocationUtil(Client client) {
        this.client = client;
    }

    public GeoLocation getLocationFromExternalAPI(String ip) {
        WebTarget webTarget = client.target(url + ip);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        GeoLocation location = response.readEntity(GeoLocation.class);
        System.out.println(location.toString());
        // todo save in database
        return location;
    }
}
