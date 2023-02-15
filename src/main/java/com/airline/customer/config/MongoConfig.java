package com.airline.customer.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
@ConfigurationProperties("spring.data.mongodb")
@ComponentScan (basePackages = {"com.airline"})
@EnableMongoRepositories(basePackages = "com.airline.customer")
public class MongoConfig extends AbstractMongoClientConfiguration {

    String uri;

    @Override
    protected String getDatabaseName() {
        return uri;
    }
 
    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(uri+"/customer");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.airline");
    }
}
