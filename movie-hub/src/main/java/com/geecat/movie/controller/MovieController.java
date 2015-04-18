package com.geecat.movie.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geecat.movie.request.MovieRequest;
import com.geecat.movie.response.MovieResponse;

/**
 * Handles requests for movie related functionality.
 */
@Controller
@RequestMapping("/movies")
public class MovieController {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	/**
	 * 
	 * 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes={"application/json"}, produces={"application/json"})
	public @ResponseBody MovieRequest addMovie(Locale locale,@RequestBody MovieRequest movie) {
		logger.info("Entering movie /add to add {}",movie.toString());
		MovieResponse response = new MovieResponse();
		
		
		return  movie;
	}
	
}
