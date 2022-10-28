package com.project.geo.domain.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GeoLocation {
    @JsonProperty
    private String id;

    @NotNull
    @JsonProperty
    private String query;
    @JsonProperty
    private String status;
    @JsonProperty
    private String country;
    @JsonProperty
    private String countryCode;
    @JsonProperty
    private String region;
    @JsonProperty
    private String regionName;
    @JsonProperty
    private String city;
    @JsonProperty
    private String zip;
    @JsonProperty
    private String lat;
    @JsonProperty
    private String lon;
    @JsonProperty
    private String timezone;
    @JsonProperty
    private String isp;
    @JsonProperty
    private String org;
    @JsonProperty
    private String asset;
}
