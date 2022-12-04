package com.lionani07.helpdesk.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HazelcastConfig {

    @Bean
    public Config config() {
        log.info("Configurando hazelcast");
        final Config config = new Config();

        final NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.setPort(5701);
        final JoinConfig join = networkConfig.getJoin();
        join.getMulticastConfig().setEnabled(false);
        join.getTcpIpConfig().addMember("machine1")
                .addMember("localhost").setEnabled(true);

        log.info("Cazelcast configurado com succeso");
        return config;
    }

    @Bean
    public HazelcastInstance instance(Config config) {
        config.setInstanceName( "my-instance" );
        return Hazelcast.newHazelcastInstance(config);
    }
}
