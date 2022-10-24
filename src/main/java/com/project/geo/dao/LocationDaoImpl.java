package com.project.geo.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(LocationMapper.class)
public interface LocationDaoImpl {

    @SqlQuery("select * from geolocation;")
    public List<GeoLocation> getLocations();

    @SqlQuery("select * from geolocation where id = :id")
    public GeoLocation getLocation(@Bind("id") final String id);

    @SqlQuery("select * from geolocation where query = :query")
    public GeoLocation getLocationByIP(@Bind("query") final String query);

    @SqlUpdate("insert into geolocation(id, query, status, country, countryCode, region, regionName, " +
            "city, zip, lat, lon, timezone, isp, org, asset) values(:id, :query, :status, :country, :countryCode, :region, " +
            ":regionName, :city, :zip, :lat, :lon, :timezone, :isp, :org, :asset)")
    void createLocation(@BindBean final GeoLocation location);

    @SqlUpdate("update geolocation set query = coalesce(:query, query) where id = :id")
    void editLocation(@BindBean final GeoLocation location);

    @SqlUpdate("delete from geolocation where id = :id")
    int deleteLocation(@Bind("id") final String id);

    @SqlQuery("select last_insert_id();")
    public String lastInsertId();
}

