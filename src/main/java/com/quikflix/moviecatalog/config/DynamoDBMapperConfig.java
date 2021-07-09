package com.quikflix.moviecatalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Configuration for DynamoDBMapper bean
 * 
 */
@Configuration
public class DynamoDBMapperConfig {
	
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(dynamoDB);
	}
	
	@Autowired
	private AmazonDynamoDB dynamoDB;

}
