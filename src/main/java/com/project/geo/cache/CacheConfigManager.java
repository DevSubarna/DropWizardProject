package com.project.geo.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.project.geo.domain.Location;
import com.project.geo.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfigManager.class);

    private static CacheConfigManager cacheConfigManager = new CacheConfigManager();

    public static CacheConfigManager getInstance() {
        return cacheConfigManager;
    }

    private static LoadingCache<String, Location> locationCache;

    public void initLocationCache(LocationService locationService) {
        if(locationCache == null) {
            locationCache = CacheBuilder.newBuilder().concurrencyLevel(10)
                    .maximumSize(200)
                    .expireAfterAccess(30, TimeUnit.MINUTES)
                    .recordStats()
                    .build(new CacheLoader<String, Location>() {
                        @Override
                        public Location load(String query) throws Exception {
                            logger.info("Fetching Location Data from DB/ Cache Miss");
                            return locationService.getLocationByQuery(query);
                        }
                    });
        }
    }

    public Location getLocationDataFromCache(String query) {
        try {
            CacheStats cacheStats = locationCache.stats();
            logger.info("CacheStats = {} ", cacheStats);
            return locationCache.get(query);
        } catch (ExecutionException e) {
            logger.error("Error Retrieving Elements from the Location Cache"
                    + e.getMessage());
        }
        return null;
    }

}
