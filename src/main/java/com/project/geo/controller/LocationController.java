package com.project.geo.controller;

import com.codahale.metrics.annotation.Timed;
import com.project.geo.domain.Location;
import com.project.geo.domain.LocationDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationController {

//    private final LocationService locationService;

//    public LocationController(LocationService locationService, Validator validator) {
//        this.validator = validator;
//        this.locationService = locationService;
//    }


//    public LocationController(LocationService locationService) {
//        this.locationService = locationService;
//    }

    private final LocationDAO locationDAO;
    private final Validator validator ;

    public LocationController(LocationDAO locationDAO, Validator validation) {
        this.locationDAO = locationDAO;
        this.validator = validation;
    }

    @GET
    @Path("{query}")
    @Timed
    @UnitOfWork
    public Location getLocationInfo(@PathParam("query") final String query) {
        return locationDAO.findByIp(query);
    }

    @POST
    @Path("/")
    @Timed
    @UnitOfWork
    public Location createLocationInfo(@NotNull @Valid final Location location) {
        return locationDAO.createLocation(location);
    }

//    @GET
//    public Response getLocation() {
//        return Response.ok("").build();
//    }

    @GET
    @Path("/health")
    @Timed
    @UnitOfWork
    public String healthCheck() {
        return "Ping received at " + new Date();
    }

//    @GET
//    @Path("{query}")
//    @Timed
//    @UnitOfWork
//    public Representation<Location> getLocationInfo(@PathParam("query") final String query) {
//        return new Representation<Location>(HttpStatus.OK_200, locationService.getLocationByQuery(query));
//    }
//
//    @POST
//    @Path("/ip")
//    @Timed
//    @UnitOfWork
//    public Representation<Location> createLocationInfo(@NotNull @Valid final Location location) {
//        return new Representation<Location>(HttpStatus.OK_200,locationService.createLocationInfo(location));
//    }
}
