package com.sumit.movieticketbooking.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumit.movieticketbooking.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
