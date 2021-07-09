package com.quikflix.moviecatalog.config;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * Creates the schema for a local DynamoDB table,
 * if the spring profile is set to local
 *
 */
@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "local")
@Configuration
@Slf4j
public class DynamoDBLocalConfig {

	/**
	 * Creates a local DynamoDB table given the config in application.yml
	 * 
	 */
	@PostConstruct
	void createLocalDynamoDB() {
		
		try {
		
			log.info("Creating local DynamoDB table " + tableName);
			
			ArrayList<AttributeDefinition> attributeDefinitions= new ArrayList<AttributeDefinition>();
			attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("S"));
			
			ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH));
			
			CreateTableRequest request = new CreateTableRequest()
			        .withTableName(tableName)
			        .withKeySchema(keySchema)
			        .withAttributeDefinitions(attributeDefinitions)
			        .withProvisionedThroughput(new ProvisionedThroughput()
			            .withReadCapacityUnits(5L)
			            .withWriteCapacityUnits(6L));
			
			CreateTableResult result = dynamoDB.createTable(request);
	
			log.info("Successfully created table: " + result.getTableDescription());
			
		} catch(ResourceInUseException e) {
			
			log.info("local table " + tableName + " already exists");
			
		}
	
			
	}
	
	@Autowired
	private AmazonDynamoDB dynamoDB;
	
	@Value("${dynamodb.tableName}")
	private String tableName;
}
