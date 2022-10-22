package com.project.geo.resource;

import com.codahale.metrics.health.HealthCheck;
import com.project.geo.service.LocationService;

public class GeolocationHealthCheckResource extends HealthCheck {

    private static final String HEALTHY_MESSAGE = "The Dropwizard blog Service is healthy for read and write";
    private static final String UNHEALTHY_MESSAGE = "The Dropwizard blog Service is not healthy. ";

    private final LocationService locationService;

    public GeolocationHealthCheckResource(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    protected Result check() throws Exception {
        String mySqlHealthStatus = locationService.performHealthCheck();

        if (mySqlHealthStatus == null) {
            return Result.healthy(HEALTHY_MESSAGE);
        } else {
            return Result.unhealthy(UNHEALTHY_MESSAGE , mySqlHealthStatus);
        }
    }
}
