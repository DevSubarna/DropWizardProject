package com.project.geo.service;

import com.project.geo.dao.GeoLocation;
import com.project.geo.domain.Location;
import com.project.geo.dao.LocationDaoImpl;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.core.statement.UnableToCreateStatementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import java.util.Objects;

public abstract class LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

    private static final String DATABASE_ACCESS_ERROR =
            "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
    private static final String DATABASE_CONNECTION_ERROR =
            "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
    private static final String UNEXPECTED_DATABASE_ERROR =
            "Unexpected error occurred while attempting to reach the database. Details: ";
    private static final String SUCCESS = "Success";
    private static final String UNEXPECTED_DELETE_ERROR = "An unexpected error occurred while deleting location.";
    private static final String LOCATION_NOT_FOUND = "Location id %s not found.";

    @CreateSqlObject
    abstract LocationDaoImpl locationDaoImpl();

    public GeoLocation getLocation(String id) {
        GeoLocation location = locationDaoImpl().getLocation(id);
        logger.info(location.toString());
        if (Objects.isNull(location)) {
            throw new WebApplicationException(String.format(LOCATION_NOT_FOUND, id), Status.NOT_FOUND);
        }
        return location;
    }

    public GeoLocation getLocationByQuery(String query) {
        GeoLocation location = locationDaoImpl().getLocationByIP(query);
        if (Objects.isNull(location)) {
            throw new WebApplicationException(String.format(LOCATION_NOT_FOUND, query), Status.NOT_FOUND);
        }
        return location;
    }

    public GeoLocation createLocationInfo(GeoLocation location) {
        locationDaoImpl().createLocation(location);
        return locationDaoImpl().getLocation(locationDaoImpl().lastInsertId());
    }

//    public GeoLocation editLocation(GeoLocation location) {
//        if (Objects.isNull(locationDaoImpl().getLocation(location.getId()))) {
//            throw new WebApplicationException(String.format(LOCATION_NOT_FOUND, location.getId()),
//                    Status.NOT_FOUND);
//        }
//        locationDaoImpl().editLocation(location);
//        return locationDaoImpl().getLocation(location.getId());
//    }

    public String deleteLocation(final String id) {
        int result = locationDaoImpl().deleteLocation(id);
        switch (result) {
            case 1:
                return SUCCESS;
            case 0:
                throw new WebApplicationException(String.format(LOCATION_NOT_FOUND, id), Status.NOT_FOUND);
            default:
                throw new WebApplicationException(UNEXPECTED_DELETE_ERROR, Status.INTERNAL_SERVER_ERROR);
        }
    }

    public String performHealthCheck() {
        try {
            locationDaoImpl().getLocations();
        } catch (UnableToCreateStatementException ex) {
            return checkUnableToCreateStatementException(ex);
        } catch (UnableToExecuteStatementException ex) {
            return checkUnableToExecuteStatementException(ex);
        } catch (Exception ex) {
            return UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
        }
        return null;
    }

    private String checkUnableToCreateStatementException(UnableToCreateStatementException ex) {
        if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
            return DATABASE_ACCESS_ERROR + ex.getCause().getLocalizedMessage();
        } else if (ex.getCause() instanceof java.sql.SQLException) {
            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
        } else {
            return UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
        }
    }

    private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
        if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
        } else {
            return UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
        }
    }
}
