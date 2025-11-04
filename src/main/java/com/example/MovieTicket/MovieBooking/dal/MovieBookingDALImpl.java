package com.example.MovieTicket.MovieBooking.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.communicator.RatingRestCommunicator;

@Repository
public class MovieBookingDALImpl implements MovieBookingDAL {
	List<Movie> movies = new ArrayList<Movie>();
	HashMap<String, Movie> movieMap = new HashMap<>();


	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movies;
	}

	@Override
	public void createMovie(Movie movie) {
		// TODO Auto-generated method stub
		String movieId = movie.getId();
		movies.add(movie);
		movieMap.put(movieId, movie);

	}

	@Override
	public Movie getMovieById(String id) {
		// TODO Auto-generated method stub
		
		Movie movie = movieMap.get(id);
		
		return movie;
	}

	@Override
	public void deleteMovie(String id) {
		// TODO Auto-generated method stub
		Movie movie = movieMap.get(id);
		movies.remove(movie);
		movieMap.remove(id);
	}

	@Override
	public void updateMovieById(Movie topic, String id) {
		// TODO Auto-generated method stub
//		it is recommended to go this way of modifying existing obj than delete it and adds new one as it 
//		aligns well with put method where the other is actually post method
		Movie movie = movieMap.get(id);
		movie.setMovieName(topic.getMovieName());
		movie.setMovieDirector(topic.getMovieDirector());
		movie.setMovieRating(topic.getMovieRating());
		movie.setMovieLanguage(topic.getMovieLanguage());
		movie.setWriters(topic.getWriters());
		movie.setActors(topic.getActors());
		movie.setGenre(topic.getGenre());

	}

}
