package com.darwin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@Entity
@Table
public class GovernmentSeat {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
//	@OneToMany(mappedBy = "governmentSeat")
	@Column(name="seat_id")
	private Long seatId;
	private String title;
	private String location;
	private String city;
	private String state;
	private boolean isCompleted;
	
	@CreationTimestamp
    protected Date createdAt;

    @UpdateTimestamp
    protected Date updatedAt;

}
