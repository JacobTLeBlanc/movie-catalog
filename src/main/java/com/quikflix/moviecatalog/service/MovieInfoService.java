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
	
	@Autowired
	private DynamoDBMapper mapper;

}
