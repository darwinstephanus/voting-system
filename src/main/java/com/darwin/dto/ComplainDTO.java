package com.darwin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplainDTO {

	private Long complainId;
	private Long voterId;
	private String type;
	private String complain;
}
