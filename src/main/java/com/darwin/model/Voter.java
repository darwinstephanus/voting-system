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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table
@NoArgsConstructor
public class Voter {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "voter_id")
	private long voterId;
	private String voterFirstName;
	private String voterLastName;
	private String email;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String ssn;
	private String address;
	private String city;
	private String state;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

//	@OneToMany(mappedBy = "voter")
//	private List<Complain>complains;

	@CreationTimestamp
	protected Date createdAt;

	public Voter(String voterFirstName, String voterLastName, String email, String username, String ssn, String address,
			String city, String state) {
		super();
		this.voterFirstName = voterFirstName;
		this.voterLastName = voterLastName;
		this.email = email;
		this.username = username;
		this.ssn = ssn;
		this.address = address;
		this.city = city;
		this.state = state;
	}

	@UpdateTimestamp
	protected Date updatedAt;

}
