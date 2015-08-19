package org.jcolombo;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

public class JCacheSetter {

    public static void main(String[] args) {
        put();
    }

    private static void put() {

        System.setProperty("hazelcast.jcache.provider.type", "server");

        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        //configure the cache
        MutableConfiguration<String, String> config = new MutableConfiguration<String, String>();
        config.setStoreByValue(true)
                .setTypes(String.class, String.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.FIVE_MINUTES))
                .setStatisticsEnabled(true);


        cacheManager.destroyCache("MY_CACHE");
        //create the cache
        cacheManager.createCache("MY_CACHE", config);

        //get the cache
        Cache<String, String> cache = cacheManager.getCache("MY_CACHE", String.class, String.class);
        cache.put("theKey", "Hello World");
        String value = cache.get("theKey");

        System.out.println("echo cache.get('theKey') = " + value);//prints 'Hello World'

        for (int increment = 0; increment < 100; increment++) {
            cache.put("key-" + increment, "Hello World - " + increment);
        }

        System.out.println("End");
    }


}
