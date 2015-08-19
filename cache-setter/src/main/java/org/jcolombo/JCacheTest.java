/*
 * Copyright 2015 WSO2, Inc. (http://wso2.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jcolombo;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

public class JCacheTest {

    public static void main(String[] args) {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        Cache<String, User> cache = cacheManager.getCache("Users", String.class, User.class);
        cache.put("Bob", new User("Bob", "bob@example.com"));
        User user = cache.get("Bob");


        MutableConfiguration<String, User> config =
                new MutableConfiguration<String, User>();
        config.setTypes(String.class, User.class)
                .setStatisticsEnabled(true)
                .setStoreByValue(true)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.
                        factoryOf(Duration.ONE_MINUTE));

        cacheManager.createCache("Orders", config);

    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}
