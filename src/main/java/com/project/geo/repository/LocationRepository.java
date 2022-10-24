package com.project.geo.repository;

import com.project.geo.cache.CacheConfigManager;
import com.project.geo.domain.Location;
import com.project.geo.util.LocationUtil;

import javax.ws.rs.client.Client;
import java.util.Objects;

public class LocationRepository {

    public final CacheConfigManager cacheConfigManager;
    public final Client client;

    public LocationRepository(CacheConfigManager cacheConfigManager, Client client) {
        this.cacheConfigManager = cacheConfigManager;
        this.client = client;
    }

    public Location findByIP(final String ip) {
        if(Objects.isNull(cacheConfigManager.getLocationDataFromCache(ip))) {
            return LocationUtil.getLocationFromExternalAPI(client,ip);
        } else return cacheConfigManager.getLocationDataFromCache(ip);
    }

}
