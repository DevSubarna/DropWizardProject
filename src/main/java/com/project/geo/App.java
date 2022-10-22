package com.project.geo;

import com.project.geo.config.GeoConfiguration;
import com.project.geo.controller.LocationController;
import com.project.geo.dao.LocationDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class App extends Application<GeoConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String SQL = "sql";
    private static final String DROPWIZARD_MYSQL_SERVICE = "Dropwizard MySQL Service";

    @Override
    public void initialize(Bootstrap<GeoConfiguration> b) {
    }

    @Override
    public void run(GeoConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Resource Registering");
        final DataSource dataSource =
                configuration.getDataSourceFactory().build(environment.metrics(), SQL);
        DBI dbi = new DBI(dataSource);
        environment.jersey().register(new LocationController(new LocationDAO(), environment.getValidator()));
    }

    public static void main(String[] args) throws Exception{
        new App().run("server", args[0]);
        System.out.println("Running");
    }
}
