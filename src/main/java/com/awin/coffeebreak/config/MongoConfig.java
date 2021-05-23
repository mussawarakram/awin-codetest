package com.awin.coffeebreak.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String MONGO_DB_URL;

    @Value("${spring.data.mongodb.database}")
    private String MONGO_DB_NAME;

    @Value("${spring.data.mongodb.port}")
    private String MONGO_DB_PORT;

    /**
     * This bean binds the embedded Mongo instance and allows access via Spring Data
     * @return a Spring Data MongoTemplate object
     */
    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClient mongoClient = MongoClients.create("mongodb://" + MONGO_DB_URL + ":" + MONGO_DB_PORT);
        return new MongoTemplate(mongoClient, MONGO_DB_NAME);
    }
}