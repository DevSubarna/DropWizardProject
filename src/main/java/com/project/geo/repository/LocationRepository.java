package com.project.geo.repository;

import com.project.geo.domain.LocationDAO;
import com.project.geo.domain.jdbi.LocationJDBIImpl;
import com.project.geo.domain.model.GeoLocation;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.UUID;

public class LocationRepository implements LocationDAO {

    private final Jdbi jdbi;

    public LocationRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public List<GeoLocation> getLocations() {
        return jdbi.withExtension(LocationJDBIImpl.class, dao -> dao.getLocations());
    }

    @Override
    public GeoLocation getLocation(String id) {
        return jdbi.withExtension(LocationJDBIImpl.class, dao -> dao.getLocation(id));
    }

    @Override
    public GeoLocation getLocationByIP(String query) {
        return jdbi.withExtension(LocationJDBIImpl.class, dao -> dao.getLocationByIP(query));
    }

    @Override
    public GeoLocation createLocation(GeoLocation location) {
        String lastId = jdbi.withExtension(LocationJDBIImpl.class, dao -> {
            String id = UUID.randomUUID().toString();
            location.setId(id);
            dao.createLocation(location);
            return dao.lastInsertId();
        });
        return getLocation(lastId);
    }

    @Override
    public String lastInsertId() {
        return jdbi.withExtension(LocationJDBIImpl.class, dao -> dao.lastInsertId());
    }
}
