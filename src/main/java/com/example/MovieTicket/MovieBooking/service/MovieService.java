package com.example.MovieTicket.MovieBooking.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.communicator.RatingRestCommunicator;
@Service
public class MovieService implements MovieServiceInterface{
	
	List<Movie> movies=new ArrayList<Movie>();
	HashMap<String,Movie> movieMap=new HashMap<>();
	@Autowired
	RatingRestCommunicator ratingRestCommunicator;

	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movies;
	}

	@Override
	public void createMovie(Movie movie) {
		// TODO Auto-generated method stub
		 for (Movie existingMovie : movies) {
	            if (existingMovie.getId().equals(movie.getId())) {
	                throw new IdAlreadyExist("Movie with ID " + movie.getId() + " already exists.");
	            }
	        }
		String movieId=movie.getId();
		movies.add(movie);
		movieMap.put(movieId, movie);
		
		HashMap<String,Long> ratingMap=new HashMap<>();
		ratingMap.put(movie.getId(), movie.getMovieRating());
		ratingRestCommunicator.addRating(ratingMap);
	}

	@Override
	public Movie getMovieById(String id) {
		// TODO Auto-generated method stub
		if (!(movieMap.containsKey(id))) {
		    throw new IdNotFound("Id Not Found");
		}
		Movie movie= movieMap.get(id);
		movie.setMovieRating(ratingRestCommunicator.getRating(id));
		return movie;
	}

	@Override
	public void deleteMovie(String id) {
		// TODO Auto-generated method stub
		if (!(movieMap.containsKey(id))) {
		    throw new IdNotFound("Id Not Found");
		}
		Movie movie=movieMap.get(id);
		movies.remove(movie);
		movieMap.remove(id);
		ratingRestCommunicator.deleteRating(id);
	}

	@Override
	public void updateMovieById(Movie topic,String id) {
		// TODO Auto-generated method stub
		if (!(movieMap.containsKey(id))) {
		    throw new IdNotFound("Id Not Found");
		}
//		it is recommended to go this way of modifying existing obj than delete it and adds new one as it 
//		aligns well with put method where the other is actually post method
		Movie movie=movieMap.get(id);
		 movie.setMovieName(topic.getMovieName());
	        movie.setMovieDirector(topic.getMovieDirector());
	        movie.setMovieRating(topic.getMovieRating());
	        movie.setMovieLanguage(topic.getMovieLanguage());
	        movie.setWriters(topic.getWriters());
	        movie.setActors(topic.getActors());
	        movie.setGenre(topic.getGenre());
		
		HashMap<String,Long> ratingMap=new HashMap<>();
		ratingMap.put(topic.getId(), topic.getMovieRating());
		ratingRestCommunicator.updateRating(ratingMap);
	}

}
