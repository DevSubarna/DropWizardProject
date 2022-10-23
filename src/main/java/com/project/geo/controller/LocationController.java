package com.project.geo.controller;

import com.codahale.metrics.annotation.Timed;
import com.project.geo.dao.Location;
import com.project.geo.service.LocationService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationController {
    private final Validator validator;
    private final LocationService locationService;

    public LocationController(LocationService locationService, Validator validator) {
        this.validator = validator;
        this.locationService = locationService;
    }

    @GET
    public Response getLocation() {
        return Response.ok("").build();
    }

    @GET
    @Path("/health")
    @Timed
    @UnitOfWork
    public String healthCheck() {
        return "Ping received at " + new Date();
    }

    @GET
    @Path("{id}")
    @Timed
    @UnitOfWork
    public Response getLocationInfo(@PathParam("id") String id) {
        //return Response.ok(locationService.getLocation(id).build());
        return Response.ok().build();
    }

    @POST
    @Path("/ip")
    @Timed
    @UnitOfWork
    public Response createLocationInfo(@NotNull @Valid final Location location) {
//        Location newLocation = new Location(location.getId(), lo)
        return Response.ok().build();
    }

}
