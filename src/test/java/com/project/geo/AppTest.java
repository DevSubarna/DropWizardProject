package com.project.geo;

import com.project.geo.config.GeoConfiguration;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.DropwizardTestSupport;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AppTest {

    public static DropwizardAppExtension<GeoConfiguration> DROPWIZARD = new DropwizardAppExtension<>(App.class, ResourceHelpers.resourceFilePath("dropwizardmysql.yml"));

    @Test
    public void urlTestafterPost() {
//        Client client = DROPWIZARD.client();
//        Response response = client.target(
//                        String.format("http://localhost:%d/app/healthy", DROPWIZARD.getLocalPort()))
//                .request()
//                .post(Entity.text("healthy"));
//        assertThat(response.getStatus()).isEqualTo(200);
    }

}
