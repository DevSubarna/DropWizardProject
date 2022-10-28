package com.project.geo;

import com.project.geo.config.GeoConfiguration;
import com.project.geo.controller.LocationController;
import com.project.geo.domain.Location;
import com.project.geo.domain.LocationDAO;
import com.project.geo.cache.CacheConfigManager;
import com.project.geo.test.repositoryS.LocationRepository;
import com.project.geo.test.resourceS.LocationResource;
import com.project.geo.test.util.LocationUtils;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import javax.ws.rs.client.Client;

public class App extends Application<GeoConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String SQL = "sql";
    private static final String DROPWIZARD_LOCATION = "Dropwizard Location Service";

    @Override
    public void initialize(Bootstrap<GeoConfiguration> b) {
        //b.addBundle(hibernateBundle);
        b.addBundle(new JdbiExceptionsBundle());
    }

    @Override
    public void run(GeoConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Resource Registering");

        //usingHibernate(configuration, environment);


//        final LocationUtil locationUtil = new LocationUtil(client);
//        environment.jersey().register(locationUtil);

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(),SQL);


//        final LocationDaoImpl dao = jdbi.onDemand(LocationDaoImpl.class);
//        final CacheConfigManager cacheConfigManager = CacheConfigManager
//                .getInstance();
//        cacheConfigManager.initLocationCache(dao);
//        final LocationJdbiController controller = new LocationJdbiController(dao, cacheConfigManager, locationUtil);
//        environment.jersey().register(controller);

        // change structure
        final LocationRepository locationRepository = new LocationRepository(jdbi);
        final CacheConfigManager cache = CacheConfigManager.getInstance();
        cache.initLocationCache(locationRepository);
        final Client client = new JerseyClientBuilder(environment).build("dropwizardclient");
        final LocationUtils locationUtils = new LocationUtils(client);
        environment.jersey().register(new LocationResource(new LocationRepository(jdbi),cache,locationUtils));


        //cacheConfigManager.initLocationCache(jdbi.onDemand(LocationService.class));

        //environment.jersey().register(new CacheResource());


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
