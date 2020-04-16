package com.darwin;

import org.springframework.boot.SpringApplication;
import org.springframework.core.SpringVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VotingSystemApplication {

	public static void main(String[] args) {
		System.out.println("version: " + SpringVersion.getVersion());
		SpringApplication.run(VotingSystemApplication.class, args);
	}

}
