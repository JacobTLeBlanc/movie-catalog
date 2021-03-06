package com.quikflix.moviecatalog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

/**
 * DynamoDB Config from application.yml
 *
 */
@Configuration
public class DynamoDBConfig {
	
	@Bean
	public AmazonDynamoDB AmazonDynamoDB() {
		
		 return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new EndpointConfiguration(endpoint, region))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();
		
	}
	
	@Value("${dynamodb.endpoint}")
	private String endpoint;
	
	@Value("${dynamodb.region}")
	private String region;
	
	@Value("${dynamodb.accessKey}")
	private String accessKey;
	
	@Value("${dynamodb.secretKey}")
	private String secretKey;
	
}
