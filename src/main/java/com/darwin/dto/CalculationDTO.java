package com.darwin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculationDTO {

	private Long electionId;
	private Long candidateId;
	private Long numberOfVoters;
	private Boolean isCompleted;
}
