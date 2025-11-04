package com.example.MovieTicket.MovieBooking.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.communicator.RatingRestCommunicator;
import com.example.MovieTicket.MovieBooking.dal.MovieBookingDAL;
@Service
public class MovieService implements MovieServiceInterface{
	
	@Autowired
	RatingRestCommunicator ratingRestCommunicator;
	@Autowired
	MovieBookingDAL movieBookingDAL;

	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movieBookingDAL.getAllMovies();
	}

	@Override
	public void createMovie(Movie movie) {
		// TODO Auto-generated method stub
		String movieId=movie.getId();
		Movie oldMovie= movieBookingDAL.getMovieById(movieId);
		// TODO Auto-generated method stub
		if (oldMovie!=null) {
		    throw new IdAlreadyExist("Id Already exists");
		}
		movieBookingDAL.createMovie(movie);
		
		HashMap<String,Long> ratingMap=new HashMap<>();
		ratingMap.put(movieId, movie.getMovieRating());
		ratingRestCommunicator.addRating(ratingMap);
	}

	@Override
	public Movie getMovieById(String id) {
		Movie movie= movieBookingDAL.getMovieById(id);
		// TODO Auto-generated method stub
		if (movie==null) {
		    throw new IdNotFound("Id Not Found");
		}
		movie.setMovieRating(ratingRestCommunicator.getRating(id));
		return movie;
	}

	@Override
	public void deleteMovie(String id) {
		// TODO Auto-generated method stub
		Movie movie= movieBookingDAL.getMovieById(id);
		// TODO Auto-generated method stub
		if (movie==null) {
		    throw new IdNotFound("Id Not Found");
		}
		movieBookingDAL.deleteMovie(id);
		ratingRestCommunicator.deleteRating(id);
	}

	@Override
	public void updateMovieById(Movie topic,String id) {
		// TODO Auto-generated method stub
		Movie movie= movieBookingDAL.getMovieById(id);
		// TODO Auto-generated method stub
		if (movie==null) {
		    throw new IdNotFound("Id Not Found");
		}
		movieBookingDAL.updateMovieById(topic, id);
		
		HashMap<String,Long> ratingMap=new HashMap<>();
		ratingMap.put(topic.getId(), topic.getMovieRating());
		ratingRestCommunicator.updateRating(ratingMap);
	}

}
