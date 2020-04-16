package com.darwin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain=true)
@Table
@NoArgsConstructor
public class Admin {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long adminId;
	private String adminFirstName;
	private String adminLastName;
	private String email;
	@Column(unique=true)
	private String username;
//	private String password;
	
	
	
	@CreationTimestamp
    protected Date createdAt;

    public Admin(String adminFirstName, String adminLastName, String email, String username) {
    	super();
    	this.adminFirstName = adminFirstName;
    	this.adminLastName = adminLastName;
    	this.email = email;
    	this.username = username;
    }

	@UpdateTimestamp
    protected Date updatedAt;
	
	
}
