package com.sumit.movieticketbooking.respository;

import com.sumit.movieticketbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
