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

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

/**
 * Sample JCache annotations
 */
public class UserManager {

    @CacheResult(cacheName = "users")
    public User getUser(@CacheKey String name) {
        //...
        return null;
    }

    @CachePut(cacheName = "users")
    public void updateUser(String name, @CacheValue User updatedUser) {
        //...
    }

    @CacheRemove(cacheName = "users")
    public void removeUser(@CacheKey String name) {
        //...
    }

    @CacheRemoveAll(cacheName = "users")
    public void removeAllUsers() {

    }

    private class User {
        private String username;
        private String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }


}
