package com.sumit.movieticketbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="table_theatre")
public class Theatre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long t_id;
	
	@NotBlank
	private String t_name;
	
	@NotBlank
	private String t_area;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	//@JoinColumn(name = "c_id")
	private City city;
	
	/*First version of creating many to one relationship between theatre and movie entity*/
//	@ManyToMany(cascade = {CascadeType.ALL})
//	@JoinTable(
//			name = "theatre_movie",
//			joinColumns = {
//					@JoinColumn(name = "T_id")
//			},
//			inverseJoinColumns = {
//					@JoinColumn(name = "M_id")
//			}
//	)
//	Set <Movie> movies = new HashSet	< Movie > (); 
//	
	
	@OneToMany(mappedBy = "theTheatre")
	private List<Show> theShow;
}
