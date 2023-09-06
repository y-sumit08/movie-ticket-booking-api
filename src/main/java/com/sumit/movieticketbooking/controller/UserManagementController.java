package com.sumit.movieticketbooking.controller;

import com.sumit.movieticketbooking.model.User;
import com.sumit.movieticketbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
@author : Sumit Yadav
@Description : User Management controller to add/delete user in the database
 */
@RestController
@RequestMapping("/api/v1/manageUser/")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value="id") int ID)
    {
        return userService.deleteUser(ID);
    }

}
