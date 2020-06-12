package com.unicodez.meetup.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "role")
public class Role {
	
	@Id
	@Column(name = "id")
	private Integer roleId;
	
	@Column(name = "name")
	private String roleName;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	private Set<User> user;
	
	public Role() {
	}

}
