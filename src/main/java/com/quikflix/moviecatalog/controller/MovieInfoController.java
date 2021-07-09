package com.quikflix.moviecatalog.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quikflix.moviecatalog.domain.MovieInfo;
import com.quikflix.moviecatalog.service.MovieInfoService;

/**
 * API Controller for movie catalog
 * 
 */
@RestController
@RequestMapping("/")
public class MovieInfoController {

	/** 
	 * Creates and adds a movie info document to DB given the parameters
	 * 
	 * @param name         name of the movie 
	 * @param director     director of the movie
	 * @param cast         cast of the movie
	 * @param category     category of the movie (drama, comedy, etc.)
	 * @param year         the year of the movies' release
	 * @param description  description of the movie's plot
	 */
	@PostMapping("/addMovieInfo")
    public void addMovieInfo(
    		@RequestParam final String name,
    		@RequestParam final String director,
    		@RequestParam final ArrayList<String> cast,
    		@RequestParam final String category,
    		@RequestParam final int year,
    		@RequestParam final String description
    		) {
		
		MovieInfo movieInfo = new MovieInfo(name, director, cast, category, year, description);
		
		movieInfoService.addMovieInfo(movieInfo);
	}
	
	/**
	 * Deletes a movie info in DB given its id
	 * 
	 * @param id id of movie info
	 */
	@PostMapping("/deleteMovieInfo")
	public void deleteMovieInfo(@RequestParam final String id) {
		movieInfoService.deleteMovieInfo(id);
	}
	
	/** 
	 * Updates a movie info in DB with the parameters given
	 * 
	 * @param id           id of movie info in DB
	 * @param name         name of the movie 
	 * @param director     director of the movie
	 * @param cast         cast of the movie
	 * @param category     category of the movie (drama, comedy, etc.)
	 * @param year         the year of the movies' release
	 * @param description  description of the movie's plot
	 */
	@PostMapping("/updateMovieInfo")
	public void updateMovieInfo(
			@RequestParam final String id,
			@RequestParam final String name,
    		@RequestParam final String director,
    		@RequestParam final ArrayList<String> cast,
    		@RequestParam final String category,
    		@RequestParam final int year,
    		@RequestParam final String description
			) {
		MovieInfo movieInfo = new MovieInfo(name, director, cast, category, year, description);
		movieInfo.setId(id);
		
		movieInfoService.addMovieInfo(movieInfo);
	}
	
	/**
	 * Gets a movie info document from DB given its id
	 * 
	 * @param id id of movie info
	 * @return JSON representation of movie info object
	 */
	@GetMapping("/getMovieInfo")
	public MovieInfo getMovieInfo(@RequestParam final String id) {
		return movieInfoService.getMovieInfo(id);
	}
	
	@Autowired
	private MovieInfoService movieInfoService;
}
