package Cache;

import SQL.sqlTable;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.concurrent.TimeUnit;

public class CacheHelper implements Runnable{
    //Initializes cache
    private CacheManager cacheManager;
    public Cache<Long, String> preConfigured;
    public Cache<Integer, sqlTable> myCache;


    public CacheHelper (){
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("preConfigured",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))
                        .withExpiry(Expirations.timeToIdleExpiration(Duration.of(5, TimeUnit.SECONDS))))
                .build();
        cacheManager.init();

        preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);
        myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, sqlTable.class, ResourcePoolsBuilder.heap(10)));

    }

    @Override
    public void run() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("preConfigured",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))
                        .withExpiry(Expirations.timeToIdleExpiration(Duration.of(5, TimeUnit.SECONDS))))
                .build();
        cacheManager.init();

        preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);
        myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, sqlTable.class, ResourcePoolsBuilder.heap(10)));
    }
}
