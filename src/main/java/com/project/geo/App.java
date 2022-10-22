package com.project.geo;

import com.project.geo.config.GeoConfiguration;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<GeoConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<GeoConfiguration> b) {
    }

    @Override
    public void run(GeoConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Resource Registering");

        //environment.jersey().register();
    }

    public static void main(String[] args) throws Exception{
        new App().run(args);
        System.out.println("Running");
    }
}
