package com.darwin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class CandidateDTO {

	private Long candidateId;
	private String candidateFirstName;
	private String candidateLastName;
	private String city;
	private String state;
	private String contactNumber;	
	private String party;
	private String email;
	private String username;
	private String password;
	
}
