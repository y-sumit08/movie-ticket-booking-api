package com.sumit.movieticketbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthRequest {

    private String userName;
    private String password;
}