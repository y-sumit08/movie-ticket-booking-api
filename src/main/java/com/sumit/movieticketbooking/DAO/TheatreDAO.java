package com.sumit.movieticketbooking.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.movieticketbooking.model.City;
import com.sumit.movieticketbooking.model.Theatre;
import com.sumit.movieticketbooking.respository.TheatreRepository;

@Service
public class TheatreDAO {
	
	@Autowired
	TheatreRepository theTheatreRepository;
	
	//Save Theatre
	public Theatre save(Theatre t) {
		return theTheatreRepository.save(t);
	}
	
//	get all theatre
	public List<Theatre> getTheatre(){
		return theTheatreRepository.findAll();
	}
	
//	get Theatre by ID
	
	public Theatre findOne(long ID){
		
		return theTheatreRepository.getOne(ID);
		
	}
	
//	Delete theatre
	public void deleteTheatre(Theatre t) {
		theTheatreRepository.delete(t);
	}
	
//	Find theatre by city ID
	public List<Theatre> getTheatreByCityId(City c){
		return theTheatreRepository.findByCity(c);
	}
	
	
	

}
