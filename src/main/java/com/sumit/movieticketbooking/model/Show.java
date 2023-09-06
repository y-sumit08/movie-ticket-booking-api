package com.sumit.movieticketbooking.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="table_show")
@Getter
@Setter
public class Show {
	
	@Id
	@GeneratedValue
	private long show_Id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Theatre theTheatre;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Movie theMovie;
	
	@NotBlank
	private String show_time;

}
