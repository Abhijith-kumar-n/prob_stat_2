package com.jsonMapper.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mongodbConfig {

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb://localhost:27017");
    }
}
