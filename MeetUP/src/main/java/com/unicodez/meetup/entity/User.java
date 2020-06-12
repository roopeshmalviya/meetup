package com.unicodez.meetup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	@Size(max = 20)
	private String name;

	@Column(name = "email", unique = true, nullable = false, updatable = false)
	@NotNull(message = "Email should not be empity")
	@Email(message = "Enter a valid email")
	@Size(max = 50)
	private String email;

	@Column(name = "password")
	@NotNull(message = "Password should not be empity")
	@Size(max = 100)
	private String password;

	@Transient	
	private String verifyPassword;
 
	@ManyToOne
	@JoinColumn(name = "role_id") // Role 1 is for admin and 2 for general user
	private Role role;

//	private Integer role;
	
	@Column(name = "login_status") // status 1 is Enable, 2 is Disabled
	@NotNull
	private Integer loginStatus;
	
	
	
}
