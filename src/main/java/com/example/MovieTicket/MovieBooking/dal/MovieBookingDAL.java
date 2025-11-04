package com.example.MovieTicket.MovieBooking.dal;

import java.util.List;

import com.example.MovieTicket.MovieBooking.Model.Movie;

public interface MovieBookingDAL {

	public List<Movie> getAllMovies();

	public void createMovie(Movie movie);

	public Movie getMovieById(String id);

	public void deleteMovie(String id);

	public void updateMovieById(Movie topic, String id);
}
