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
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain=true)
@Table
public class Candidate {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
	@Column(name="candidate_id")
	private long candidateId;
	private String candidateFirstName;
	private String candidateLastName;
	private String email;
	@Column(unique=true)
	private String username;
//	private String password;
	private String city;
	private String state;
	private String contactNumber;
	private String party;
	
	@CreationTimestamp
    protected Date createdAt;

    @UpdateTimestamp
    protected Date updatedAt;
	
}
