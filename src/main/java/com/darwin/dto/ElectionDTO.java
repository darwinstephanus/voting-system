package com.darwin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectionDTO {

	private Long electionId;
	private Long candidateId;
	private Long seatId;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date electionDate;
	private Long totalVoters;
	
}
