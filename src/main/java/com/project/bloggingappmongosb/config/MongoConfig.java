package com.project.bloggingappmongosb.config;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase(mongoDatabase);
        return new MongoTemplate(mongoClient, database.getName());
    }

}
