package com;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    Config config() {
        Config config = new Config();
        ManagementCenterConfig centerConfig = new ManagementCenterConfig();
        centerConfig.setEnabled(true);
        centerConfig.setUrl("http://localhost:8888/mc");
        config.setManagementCenterConfig(centerConfig);
        config.setInstanceName("hazelcast-instance");
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getEurekaConfig().setEnabled(true)
                .setProperty("self-registration", "true")
                .setProperty("namespace", "hazelcast");
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("messages")
                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                .setEvictionPolicy(EvictionPolicy.LRU)
                .setTimeToLiveSeconds(10); // va rester dans le cache pour 10 s
        config.addMapConfig(mapConfig);
        return config;
    }
}
