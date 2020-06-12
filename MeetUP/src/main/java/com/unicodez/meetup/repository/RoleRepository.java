package com.unicodez.meetup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicodez.meetup.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}
