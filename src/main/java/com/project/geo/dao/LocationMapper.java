package com.project.geo.dao;

import com.project.geo.domain.Location;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LocationMapper implements ResultSetMapper<Location> {

    private static final String ID = "id";
    private static final String QUERY = "query";
    private static final String STATUS = "status";
    private static final String COUNTRY = "country";
    private static final String COUNTRYCODE = "countryCode";
    private static final String REGION = "region";
    private static final String REGIONNAME = "regionName";
    private static final String CITY = "city";
    private static final String ZIP = "zip";
    private static final String LAT = "lat";
    private static final String LON = "lon";
    private static final String TIMEZONE = "timezone";
    private static final String ISP = "isp";
    private static final String ORG = "org";
    private static final String AS = "as";

    @Override
    public Location map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Location location = new Location(UUID.randomUUID().toString(), resultSet.getString(QUERY),resultSet.getString(STATUS),
                resultSet.getString(COUNTRY),resultSet.getString(COUNTRYCODE),resultSet.getString(REGION),
                resultSet.getString(REGIONNAME), resultSet.getString(CITY), resultSet.getString(ZIP),
                resultSet.getString(LAT), resultSet.getString(LON),resultSet.getString(TIMEZONE), resultSet.getString(ISP),
                resultSet.getString(ORG), resultSet.getString(AS));
        location.setId(resultSet.getString(ID));
        return location;
    }
}
