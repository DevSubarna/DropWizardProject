package com.project.geo;

import com.project.geo.config.GeoConfiguration;
import com.project.geo.cache.CacheConfigManager;
import com.project.geo.repository.LocationRepository;
import com.project.geo.resource.LocationResource;
import com.project.geo.util.LocationUtils;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.client.Client;

public class App extends Application<GeoConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String SQL = "sql";

    @Override
    public void initialize(Bootstrap<GeoConfiguration> b) {
        b.addBundle(new JdbiExceptionsBundle());
    }

    @Override
    public void run(GeoConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Resource Registering");
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(),SQL);

        // change structure
        final LocationRepository locationRepository = new LocationRepository(jdbi);
        final CacheConfigManager cache = CacheConfigManager.getInstance();
        cache.initLocationCache(locationRepository);
        final Client client = new JerseyClientBuilder(environment).build("dropwizardclient");
        final LocationUtils locationUtils = new LocationUtils(client);
        environment.jersey().register(new LocationResource(new LocationRepository(jdbi),cache,locationUtils));
    }
    public static void main(String[] args) throws Exception{
        new App().run("server", args[0]);
        //new App().run(args);
        System.out.println("Running");
    }


}
