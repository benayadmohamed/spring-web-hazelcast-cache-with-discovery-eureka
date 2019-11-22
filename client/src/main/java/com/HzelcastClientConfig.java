package com;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HzelcastClientConfig {


    @Bean
    ClientConfig clientConfig() {
        ClientConfig config = new ClientConfig();
//        config.getNetworkConfig()
//                .addAddress("localhost:5701", "localhost:5702") // les mombre du cluster
//                .setSmartRouting(true); // actver le load balancer
        config.getNetworkConfig()
                .getEurekaConfig().setEnabled(true)
                .setProperty("namespace", "hazelcast");
        return config;
    }
  /*  @Bean
    public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
        return HazelcastClient.newHazelcastClient(clientConfig);

    }*/

    @Bean
    IMap<Integer, String> messages(
            @Qualifier("hazelcastInstance")
                    HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getMap("messages");
    }
}
