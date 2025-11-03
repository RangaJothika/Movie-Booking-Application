package com.example.MovieTicket.MovieBooking.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IdAlreadyExist extends RuntimeException {
	
public IdAlreadyExist(String msg) {
	super(msg);
}
}
