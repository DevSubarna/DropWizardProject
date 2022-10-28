package com.project.geo.repository;

import com.project.geo.cache.CacheConfigManager;
import com.project.geo.domain.LocationDAO;
import com.project.geo.domain.model.GeoLocation;
import com.project.geo.resource.LocationResource;
import com.project.geo.util.LocationUtils;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class LocationResourceTest {
    private static final LocationDAO locationDao = mock(LocationRepository.class);
    private static final CacheConfigManager cacheConfigManager = mock(CacheConfigManager.class);
    private static final LocationUtils locationUtils = mock(LocationUtils.class);

    private static final ResourceExtension resource = ResourceExtension.builder()
            .addResource(new LocationResource(locationDao,cacheConfigManager,locationUtils))
            .build();

    private GeoLocation geoLocation;

    @BeforeEach
    void setup() {
        geoLocation = new GeoLocation();
        geoLocation.setId("12345");
        geoLocation.setQuery("24.4.0.1");
        when(locationDao.getLocationByIP("12345")).thenReturn(geoLocation);
    }

    @AfterEach
    void tearDown() {
        reset(locationDao);
    }

    @Test
    void getLocationSuccess() {
        System.out.println(cacheConfigManager);
        System.out.println(locationDao);
        System.out.println(locationUtils);
        System.out.println(resource.getClientConfigurator());
//        GeoLocation found = resource.target("app/24.4.0.1").request().get(GeoLocation.class);
//        System.out.println(found);
//        assertThat(found.getQuery()).isEqualTo(geoLocation.getQuery());
//        verify(locationDao).getLocationByIP("24.4.0.1");

    }

}
