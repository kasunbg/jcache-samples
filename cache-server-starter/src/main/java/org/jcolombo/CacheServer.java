package org.jcolombo;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class CacheServer {

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        Config cfg = new Config();
        enableManagementCenter(cfg);

        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(cfg);


        //add a shutdown hook to shutdown hazelcast instance
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("#######Shutdown hook triggered.#######");
                hazelcastInstance.shutdown();
            }
        });

    }

    private static void enableManagementCenter(Config cfg) {
        ManagementCenterConfig mgtCenter = new ManagementCenterConfig("http://localhost:8080/mancenter", 3);
        mgtCenter.setEnabled(true);
        cfg.setManagementCenterConfig(mgtCenter);

    }


}
