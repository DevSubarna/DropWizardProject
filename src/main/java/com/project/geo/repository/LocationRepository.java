package com.project.geo.repository;

import com.project.geo.dao.GeoLocation;

import javax.ws.rs.client.Client;
import java.util.Objects;

public class LocationRepository {

    public final CacheConfigManager cacheConfigManager;
    public final Client client;

    public LocationRepository(CacheConfigManager cacheConfigManager, Client client) {
        this.cacheConfigManager = cacheConfigManager;
        this.client = client;
    }

    public GeoLocation findByIP(final String ip) {
        if(Objects.isNull(cacheConfigManager.getLocationDataFromCache(ip))) {
            return null;
            //return LocationUtil.getLocationFromExternalAPI(client,ip);
        } else return cacheConfigManager.getLocationDataFromCache(ip);
    }

}
