package com.sumit.movieticketbooking.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumit.movieticketbooking.model.Movie;
import com.sumit.movieticketbooking.model.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	
	public List<Show> findBytheMovie(Movie m);
}
