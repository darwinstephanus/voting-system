package com.darwin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain=true)
public class AdminDTO {

	private Long adminId;
	private String adminFirstName;
	private String adminLastName;
	private String email;
	private String username;
	private String password;
	public AdminDTO(String adminFirstName, String adminLastName, String email, String username, String password) {
		super();
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}
}
