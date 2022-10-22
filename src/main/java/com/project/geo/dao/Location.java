package com.project.geo.dao;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location {

    @NotNull
    private Integer id;
    @NotBlank
    private String query;
    @NotBlank
    private String status;
    @NotBlank
    private String country;
    @NotBlank
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private String lat;
    private String lon;
    private String timezone;
    private String isp;
    private String org;
    private String as;
}
