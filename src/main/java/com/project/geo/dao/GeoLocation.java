package com.project.geo.dao;

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
    @NotNull
    @JsonProperty
    private String id;

    @NotNull
    @JsonProperty
    private String query;
    private String status;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private String lat;
    private String lon;
    private String timezone;
    private String isp;
    private String org;
    private String asset;
}
