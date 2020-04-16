package com.darwin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain=true)
@Table
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;
	@Column(unique=true)
//	@NotBlank
//    @Size(min=3, max = 50)
	private String username;
	@Column
	@JsonIgnore
//	@NotBlank
//    @Size(min=3, max = 50)
	private String password;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy="userId")
//	@OneToMany(cascade = CascadeType.ALL)
//	private Set<Role> roles = new HashSet<>();
//	
	
	
	
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role roleId;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, Role roleId) {
		super();
		this.username = username;
		this.password = password;
		this.roleId = roleId;
	}
	
	
	
//	private String role;
	
//	private String roles;
}
