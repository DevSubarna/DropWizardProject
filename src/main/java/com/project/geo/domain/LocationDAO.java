package com.project.geo.domain;

import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import java.util.Optional;
import java.util.UUID;

public class LocationDAO extends AbstractDAO<Location> {
    public LocationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Location findByIp(String query) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(query).append("%");
        return get(query);
//        return list(namedQuery("com.project.geo.domain.Location.findByQuery")
//                .setParameter("query", builder.toString()));
    }

    public Optional<Location> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public Location createLocation(Location location) {
        if(StringUtils.isEmpty(location.getId()))
            location.setId(UUID.randomUUID().toString());
        return persist(location);
    }

}
