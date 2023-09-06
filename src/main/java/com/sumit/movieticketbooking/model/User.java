package com.sumit.movieticketbooking.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "table_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    private String email;
    private String role;
}