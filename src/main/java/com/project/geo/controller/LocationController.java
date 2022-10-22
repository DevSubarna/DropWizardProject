package com.project.geo.controller;

import com.project.geo.dao.LocationDAO;
import com.project.geo.resource.GeolocationHealthCheckResource;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationController {
    private final Validator validator;

    private final LocationDAO locationDAO;

    public LocationController(LocationDAO locationDAO, Validator validator) {
        this.validator = validator;
        this.locationDAO = locationDAO;
    }

    @GET
    public Response getLocation() {
        return Response.ok("").build();
    }

    @GET
    @Path("/health")
    public String healthCheck() {
        return "Ping received at " + new Date();
    }

}
