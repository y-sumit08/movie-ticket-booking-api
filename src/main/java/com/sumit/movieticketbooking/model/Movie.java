package com.sumit.movieticketbooking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "table_movie")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long m_id;

    @NotBlank
    private String m_name;

    @NotBlank
    private String m_director;


    private String m_description;

    @OneToMany(mappedBy = "theMovie")
    public List<Show> theShow;
    }
