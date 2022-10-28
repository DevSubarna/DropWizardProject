package com.project.geo.domain;


import com.project.geo.domain.model.GeoLocation;

import java.util.List;

public interface LocationDAO {
    public List<GeoLocation> getLocations();
    public GeoLocation getLocation(String id);
    public GeoLocation getLocationByIP(String query);
    public GeoLocation createLocation(GeoLocation location);
//    void editLocation(GeoLocation location);
//    int deleteLocation(String id);
    public String lastInsertId();
}
