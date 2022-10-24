package com.project.geo.controller;

import com.project.geo.dao.GeoLocation;
import com.project.geo.dao.LocationDaoImpl;
import com.project.geo.domain.Location;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Objects;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationJdbiController {
    public final LocationDaoImpl locationDaoImpl;
    public LocationJdbiController(LocationDaoImpl locationDaoImpl) {

        this.locationDaoImpl =  locationDaoImpl;
    }

    @GET
    @Path("/{query}")
    public GeoLocation getLocationInfo(@PathParam("query") final String query) {
        GeoLocation location = locationDaoImpl.getLocationByIP(query);
        if (Objects.isNull(location)) {
            throw new WebApplicationException(String.format("NOT FOUND", query), Response.Status.NOT_FOUND);
        }
        return location;
    }

    @POST
    @Path("/")
    public GeoLocation createLocationInfo(@NotNull @Valid final GeoLocation location) {
        locationDaoImpl.createLocation(location);
        return locationDaoImpl.getLocation(locationDaoImpl.lastInsertId());
    }

    @GET
    @Path("/health")
    public String healthCheck() {
        return "Ping received at " + new Date();
    }

}
