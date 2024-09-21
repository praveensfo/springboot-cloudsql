package com.spring.implementation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "users")
@Entity
public class Users {

	@Id
	@jakarta.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String role;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
