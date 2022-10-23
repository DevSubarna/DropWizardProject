package com.project.geo.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class LocationDAO extends AbstractDAO<Location> {
    public LocationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public Location findLocation(String id) {
        return get(id);
    }

    public Location save(Location location) {
        return persist(location);
    }
}
