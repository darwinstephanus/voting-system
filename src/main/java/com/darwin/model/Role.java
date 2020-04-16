package com.darwin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain=true)
@Table
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private long roleId;
	
	@Column(name="role_name", unique=true)
	private String name;
	
//	@ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User userId;
	
}
