package org.jcolombo;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.concurrent.TimeUnit;

public class JCacheGetter {

    public static void main(String[] args) {
        get();
    }

    private static void get() {

        System.setProperty("hazelcast.jcache.provider.type", "client");

        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        //configure the cache
        MutableConfiguration<String, String> config = new MutableConfiguration<String, String>();
        config.setStoreByValue(true)
                .setTypes(String.class, String.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(TimeUnit.MILLISECONDS, 10 * 1000)))
                .setStatisticsEnabled(false);

        //create the cache
        cacheManager.createCache("MY_CACHE", config);

        //get the cache
        Cache<String, String> cache = cacheManager.getCache("MY_CACHE", String.class, String.class);

        String value = cache.get("theKey");

        System.out.println(value);//prints 'Hello World'

        for (int increment = 0; increment < 100; increment++) {
            value = cache.get("key-" + increment);
            System.out.print(value + "  ");
        }

    }

}
