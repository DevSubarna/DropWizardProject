package com.project.geo.dao;

import com.project.geo.domain.Location;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(LocationMapper.class)
public interface LocationDaoImpl {

    @SqlQuery("select * from locations;")
    public List<Location> getLocations();

    @SqlQuery("select * from location where id = :id")
    public Location getLocation(@Bind("id") final Integer id);

    @SqlQuery("select * from location where query = :query")
    public Location getLocationByIP(@Bind("query") final String query);

    @SqlUpdate("insert into location(query, status, country, countryCode, region, regionName, " +
            "city, zip, lat, lon, timezone, isp, org, as) values(:query, :status, :country, :countryCode, :region, " +
            ":regionName, :city, :zip, :lat, :lon, :timezone, :isp, :org, :as)")
    void createLocation(@BindBean final Location location);

    @SqlUpdate("update location set query = coalesce(:query, query) where id = :id")
    void editLocation(@BindBean final Location location);

    @SqlUpdate("delete from location where id = :id")
    int deleteLocation(@Bind("id") final Integer id);

    @SqlQuery("select last_insert_id();")
    public Integer lastInsertId();
}

