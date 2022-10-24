package com.project.geo.domain;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "geolocation")
//@NamedQueries({
//        @NamedQuery(name = "com.project.geo.domain.Location.findByQuery",
//                    query = "select l from geolocation where l.query like :query"),
//        @NamedQuery(name = "com.project.geo.domain.Location.findById",
//                query = "select l from geolocation where l.id like :id")
//})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotEmpty
    private String id;

    @NotEmpty
//    @Column(name = "query")
    private String query;

//    @Column(name = "status")
    private String status;

//    @Column(name = "country")
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
    private String as;
}
