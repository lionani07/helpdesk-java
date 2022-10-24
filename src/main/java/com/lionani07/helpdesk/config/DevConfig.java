package com.lionani07.helpdesk.config;

import com.lionani07.helpdesk.service.DBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    final Logger logger = LoggerFactory.getLogger(DevConfig.class);

    private static final String DDL_AUTO_CREATE = "create";

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String dllAuto;

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public void instanciaDB() {
        logger.info("profile={}, ddl_auto={}", profile, dllAuto);
        if (DDL_AUTO_CREATE.equals(dllAuto)) {
            logger.info("initializing db, profile={}", profile);
            this.dbService.instanciaDB();
            logger.info("db initialized, profile={}", profile);
        }
    }
}
