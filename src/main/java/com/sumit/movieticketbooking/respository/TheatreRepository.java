package com.sumit.movieticketbooking.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.movieticketbooking.model.City;
import com.sumit.movieticketbooking.model.Theatre;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

	public List<Theatre> findByCity(City ID);
}
