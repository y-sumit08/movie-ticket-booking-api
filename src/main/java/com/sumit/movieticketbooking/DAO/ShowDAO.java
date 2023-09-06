package com.sumit.movieticketbooking.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.movieticketbooking.model.Movie;
import com.sumit.movieticketbooking.model.Show;
import com.sumit.movieticketbooking.respository.ShowRepository;

@Service
public class ShowDAO {

	@Autowired
	ShowRepository theShowRepository;
	
//	Save the show
	public Show save(Show show)
	{
		return theShowRepository.save(show);
	}
	
//	Fetch all show 
	public List<Show> fetchAllShow(){
		return theShowRepository.findAll();
		
	}
	
//	Fetch show by ID
	public Show fetchById(long Id) {
		return theShowRepository.getReferenceById(Id);
	}
	
//	Fetch show by Moive ID
	
	public List<Show> fetchByMovie(Movie m){
		return theShowRepository.findBytheMovie(m);
	}
}
