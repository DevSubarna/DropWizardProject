package com.project.geo;

import com.project.geo.config.GeoConfiguration;
import com.project.geo.controller.LocationController;
import com.project.geo.controller.RESTClientController;
import com.project.geo.dao.Location;
import com.project.geo.dao.LocationDAO;
import com.project.geo.service.LocationService;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import javax.ws.rs.client.Client;

public class App extends Application<GeoConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String SQL = "sql";
    private static final String DROPWIZARD_MYSQL_SERVICE = "Dropwizard MySQL Service";

    @Override
    public void initialize(Bootstrap<GeoConfiguration> b) {
        b.addBundle(hibernateBundle);
    }

    @Override
    public void run(GeoConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Resource Registering");
        final DataSource dataSource =
                configuration.getDataSourceFactory().build(environment.metrics(), SQL);
        //DBI dbi = new DBI(dataSource);
        LocationDAO locationDAO = new LocationDAO(hibernateBundle.getSessionFactory());
        LocationService locationService = new LocationService(locationDAO);
        environment.jersey().register(new LocationController(locationService, environment.getValidator()));

        final Client client = new JerseyClientBuilder(environment).build("dropwizardclient");
        environment.jersey().register(new RESTClientController(client));
    }

    HibernateBundle<GeoConfiguration> hibernateBundle = new HibernateBundle<GeoConfiguration>(Location.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(GeoConfiguration geoConfiguration) {
            return geoConfiguration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception{
        new App().run("server", args[0]);
        System.out.println("Running");
    }
}
