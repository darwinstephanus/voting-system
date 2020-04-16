package com.darwin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain=true)
@Table
public class Complain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long complainId;
	
	@ManyToOne
    @JoinColumn(name = "voter_id", nullable = false)
	private Voter voterId;
	
	private String type;
	private String complain;
	private Boolean isChecked;

}
