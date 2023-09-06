package com.sumit.movieticketbooking.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.movieticketbooking.model.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query(value = "select m_id, m_name, m_description from table_movie, table_show as s  where s.the_theatre_t_id = ?1", nativeQuery = true)
	public List<Object[]> getMovieByTheatreID(long ID);

}
