package com.project.geo.domain.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "geolocation")
public class GeoLocation {
    @Id
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
