package com.sumit.movieticketbooking.respository;

import com.sumit.movieticketbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {

}
