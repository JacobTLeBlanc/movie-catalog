package com.quikflix.moviecatalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.quikflix.moviecatalog.domain.MovieInfo;

/**
 * Service class for MovieInfo,
 * handles all operation with Movie_Info table
 *
 */
@Service
public class MovieInfoService {
	
	/**
	 * Adds a movie info to DB
	 * 
	 * @param movieInfo movie info to add
	 */
	public void addMovieInfo(MovieInfo movieInfo) {
		mapper.save(movieInfo);	
	}
	
	/**
	 * Deletes a movie info in the DB given its id
	 * 
	 * @param id id of movie info
	 */
	public void deleteMovieInfo(String id) {
		
		// Create 'null' movie info and set the id to the one given.
		// As long as the Id matches in DB it will be deleted.
		MovieInfo movieInfo = new MovieInfo();
		movieInfo.setId(id);
		
		mapper.delete(movieInfo);
	}
	
	/**
	 * Gets a movie info from DB given its id
	 * 
	 * @param id id of movie info
	 */
	public MovieInfo getMovieInfo(String id) {
		return mapper.load(MovieInfo.class, id, null);
	}
	
	@Autowired
	private DynamoDBMapper mapper;

}
