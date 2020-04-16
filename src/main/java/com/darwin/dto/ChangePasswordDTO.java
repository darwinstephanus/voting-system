package com.darwin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

	private String username;
	private String password;
	private String newPassword;
	
}
