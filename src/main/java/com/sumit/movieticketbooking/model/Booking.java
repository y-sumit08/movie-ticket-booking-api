package com.sumit.movieticketbooking.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@Entity
@Table(name = "table_booking")
public class Booking {
	@Id
	private long show_id;
	private int A1;
	private int A2;
	private int A3;
	private int A4;
	private int A5;
	
	private int B1;
	private int B2;
	private int B3;
	private int B4;
	private int B5;
	
	private int C1;
	private int C2;
	private int C3;
	private int C4;
	private int C5;

}
