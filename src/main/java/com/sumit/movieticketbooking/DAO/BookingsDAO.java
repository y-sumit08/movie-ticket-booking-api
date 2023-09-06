package com.sumit.movieticketbooking.DAO;

import com.sumit.movieticketbooking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.movieticketbooking.respository.BookingsRepository;

@Service
public class BookingsDAO {

	@Autowired
	BookingsRepository bookingRepository;
	
//	Get the available seat for the movie 
	public Booking getAvailableSeat(long showID) {
		return bookingRepository.getReferenceById(showID);
	}
	
//	Book the seat 
	public Booking bookTheSeat(Booking book) {
		return bookingRepository.save(book);
	}
	
}
