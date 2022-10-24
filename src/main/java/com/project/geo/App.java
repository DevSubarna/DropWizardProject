package com.project.geo;

import com.project.geo.config.GeoConfiguration;
import com.project.geo.controller.LocationController;
import com.project.geo.controller.LocationJdbiController;
import com.project.geo.dao.LocationDaoImpl;
import com.project.geo.domain.Location;
import com.project.geo.domain.LocationDAO;
import com.project.geo.service.LocationService;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class App extends Application<GeoConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String SQL = "sql";
    private static final String DROPWIZARD_LOCATION = "Dropwizard Location Service";

    @Override
    public void initialize(Bootstrap<GeoConfiguration> b) {
        b.addBundle(hibernateBundle);
    }

    @Override
    public void run(GeoConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Resource Registering");

        //usingHibernate(configuration, environment);

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(),SQL);
        final LocationDaoImpl dao = jdbi.onDemand(LocationDaoImpl.class);
        final LocationJdbiController controller = new LocationJdbiController(dao);
        environment.jersey().register(controller);


        //        final DBIFactory factory = new DBIFactory();
//        final DBI dbi = factory.build(environment, configuration.getDataSourceFactory(), SQL);
//
////        DBI dbi = new DBI(dataSource);
//        final LocationDaoImpl dao = dbi.onDemand(LocationDaoImpl.class);


        //environment.jersey().register(new LocationController(dbi.onDemand(LocationService.class)));

        //LocationService locationService = dbi.onDemand(LocationService.class);
        //LocationController locationController = new LocationController(locationService,
          //                                      environment.getValidator());
        //environment.jersey().register(locationController);

//        CacheConfigManager cacheConfigManager = CacheConfigManager
//                .getInstance();
//        cacheConfigManager.initLocationCache(dbi.onDemand(LocationService.class));
//        LOGGER.info("Registering RESTful API resources");
//        environment.jersey().register(new CacheResource());

//        final Client client = new JerseyClientBuilder(environment).build("dropwizardclient");
//        environment.jersey().register(new RESTClientController(client));
    }

    public void usingHibernate(GeoConfiguration configuration, Environment environment) {
        final DataSource dataSource =
                configuration.getDataSourceFactory().build(environment.metrics(), SQL);
        DBI dbi = new DBI(dataSource);
        final LocationDAO locationDAO = new LocationDAO(hibernateBundle.getSessionFactory());
        final LocationController locationController = new LocationController(locationDAO, environment.getValidator());
        environment.jersey().register(locationController);
    }

    HibernateBundle<GeoConfiguration> hibernateBundle = new HibernateBundle<GeoConfiguration>(Location.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(GeoConfiguration geoConfiguration) {
            return geoConfiguration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception{
        new App().run("server", args[0]);
        //new App().run(args);
        System.out.println("Running");
    }


}
