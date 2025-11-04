package com.example.MovieTicket.MovieBooking.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.service.MovieServiceInterface;

import jakarta.validation.Valid;
@RestController
public class Controller {
	@Autowired
	MovieServiceInterface movieService;
	
	@GetMapping("/ticket/movies")
public List<Movie> getAllMovies(){
	return movieService.getAllMovies();
}
	@PostMapping("/ticket/movie")
	public void createMovie(@Valid @RequestBody Movie movie,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) throw new RuntimeException("Validation Fails");
		movieService.createMovie(movie);
	}
	@GetMapping("/ticket/movie/{id}")
	public Movie getMovieById(@PathVariable("id") String id) {
		return movieService.getMovieById(id);
	}
	
	@DeleteMapping("/ticket/movie/{id}")
	public void deleteMovie(@PathVariable("id") String id) {
		 movieService.deleteMovie(id);
	}
	
	@PutMapping("/ticket/update/{id}")
	public void updateMovieById(@Valid @RequestBody Movie topic,@PathVariable("id") String id,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) throw new RuntimeException("Validation Fails");
		movieService.updateMovieById(topic,id);
	}
}
