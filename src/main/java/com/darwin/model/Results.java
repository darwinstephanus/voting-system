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
public class Results {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long resultsId;
	
	@ManyToOne
    @JoinColumn(name = "voterId", nullable = false)
	private Voter voterId;
	
//	private long electionId;
//	private long candidateId;
	
//	@ManyToOne
//    @JoinColumn(name = "seatsId", nullable = false)
//	private GovernmentSeat seatsId;
	
	@ManyToOne
    @JoinColumn(name = "electionId", nullable = false)
	private Election electionId;
	
	
//	@ManyToOne
//    @JoinColumn(name = "candidateId")
//	private Candidate candidateId;
	
}
