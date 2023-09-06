package com.sumit.movieticketbooking.service;

import com.sumit.movieticketbooking.model.User;
import com.sumit.movieticketbooking.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "user added to system ";
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "user deleted from the system";
    }
}
