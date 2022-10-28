package com.project.geo.resource;

import com.project.geo.cache.CacheConfigManager;
import com.project.geo.domain.LocationDAO;
import com.project.geo.domain.model.GeoLocation;
import com.project.geo.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {
    private final LocationDAO locationDAO;
    private final CacheConfigManager cacheConfigManager;

    private final LocationUtils locationUtils;
    private static final Logger logger = LoggerFactory.getLogger(LocationResource.class);

    public LocationResource(LocationDAO locationDAO, CacheConfigManager cacheConfigManager, LocationUtils locationUtils) {
        this.locationDAO = locationDAO;
        this.cacheConfigManager = cacheConfigManager;
        this.locationUtils = locationUtils;
    }

    @GET
    @Path("/{query}")
    public GeoLocation findByIP(@PathParam("query") final String query) {
        logger.info("Query: " + query);
        GeoLocation locationCache = cacheConfigManager.getLocationDataFromCache(query);
        logger.info("cache" + locationCache);
        logger.info("cache value" + Objects.isNull(locationCache));
        if(Objects.isNull(locationCache)) {
            locationCache = locationDAO.getLocationByIP(query);
            if (Objects.isNull(locationCache)) {
                //throw new WebApplicationException(String.format("NOT FOUND", query), Response.Status.NOT_FOUND);
                locationCache = locationUtils.getLocationFromExternalAPI(query);
                locationDAO.createLocation(locationCache);
            }
        }
        return locationCache;
    }
}
