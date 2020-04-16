package com.darwin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class VoterDTO {

	private Long voterId;
	private String voterFirstName;
	private String voterLastName;
	private String ssn;
	private String address;
	private String city;
	private String state;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String email;
	private String username;
	private String password;
	
}
