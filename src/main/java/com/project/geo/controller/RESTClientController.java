package com.project.geo.controller;

import com.project.geo.domain.Location;
import com.project.geo.util.LocationUtil;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.TEXT_PLAIN)
@Path("/location")
public class RESTClientController {
    private Client client;

    public RESTClientController(Client client) {
        this.client = client;
    }

    @GET
    @Path("/{ipaddress}")
    @UnitOfWork
    public Location getEmployeeById(@PathParam("ipaddress") String ipaddress)
    {
        return LocationUtil.getLocationFromExternalAPI(client,ipaddress);
    }
}
