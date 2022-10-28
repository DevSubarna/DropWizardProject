package com.project.geo.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.geo.domain.model.GeoLocation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.dropwizard.jackson.Jackson.newObjectMapper;

public class GeoLocationTest {

    private static final ObjectMapper MAPPER = newObjectMapper();

    @Test
    void seralizesToJSON() throws Exception {
        final GeoLocation geoLocation = new GeoLocation("12345", "24.48.0.1","success",
                "Canada","CA","QC","Quebec","Montreal","H1K","45.6085","-73.5493","America/Toronto",
                "Le Groupe Videotron Ltee","Videotron Ltee","AS5769 Videotron Telecom Ltee");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(getClass().getResource("/location/GeoLocation.json"), GeoLocation.class));

        assertThat(MAPPER.writeValueAsString(geoLocation)).isEqualTo(expected);
    }

//    @Test
//    public void deserializesFromJSON() throws Exception {
//        final GeoLocation location =  new GeoLocation("12345", "24.48.0.1","success",
//                "Canada","CA","QC","Quebec","Montreal","H1K","45.6085","-73.5493","America/Toronto",
//                "Le Groupe Videotron Ltee","Videotron Ltee","AS5769 Videotron Telecom Ltee");
//        assertThat(MAPPER.readValue(getClass().getResource("/location/GeoLocation.json"), GeoLocation.class)).isEqualTo(location);
//    }

}
