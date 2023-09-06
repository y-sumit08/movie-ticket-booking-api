 package com.sumit.movieticketbooking.model;

 import lombok.Getter;
 import lombok.Setter;

 import javax.persistence.*;
 import javax.validation.constraints.NotBlank;
 import java.util.List;

@Setter
@Getter
@Entity
@Table(name="table_city")
public class City {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long c_id;
	
	@Column(nullable=false)
	@NotBlank
	private String c_name;
	
	@NotBlank
	private String c_pincode;
	
	@NotBlank
	private String c_state;
	
	@OneToMany(mappedBy="city")
	private List<Theatre> theTheatre;
}
