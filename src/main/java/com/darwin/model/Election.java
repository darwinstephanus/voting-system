package com.darwin.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain=true)
@Table
public class Election {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long electionId;
	
	@ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidateId;
	
	@ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
	private GovernmentSeat seatId;
	private boolean isCompleted;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date electionDate;
	private Long totalVoters;

}
