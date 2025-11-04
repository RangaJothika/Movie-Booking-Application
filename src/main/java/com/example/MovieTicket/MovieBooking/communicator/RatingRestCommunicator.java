package com.example.MovieTicket.MovieBooking.communicator;

import java.util.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RatingRestCommunicator {
RestTemplate restTemplate;
public RatingRestCommunicator(RestTemplateBuilder restTemplateBuilder) {
	this.restTemplate=restTemplateBuilder.build();
}
public static final String baseUrl="http://localhost:8080/rating";
public long getRating(String id) {
	String url=baseUrl+"/id/"+id;
	ResponseEntity<Long> response=restTemplate.exchange(url, HttpMethod.GET,null,Long.class);
	return response.getBody();
}
public void addRating(Map< String,Long > ratingsMap) {
	String url=baseUrl+"/add";
	HttpEntity<Map< String,Long >> request=new HttpEntity<>(ratingsMap);
	restTemplate.exchange(url, HttpMethod.POST,request,Long.class);
}
public void updateRating(Map< String,Long> ratingsMap) {
	String url=baseUrl+"/update";
	HttpEntity<Map< String,Long >> request=new HttpEntity<>(ratingsMap);
	restTemplate.exchange(url, HttpMethod.PUT,request,Long.class);
}
public void deleteRating(String id) {
	String url=baseUrl+"/id/"+id;
	restTemplate.exchange(url, HttpMethod.DELETE,null,Long.class);
}
}
