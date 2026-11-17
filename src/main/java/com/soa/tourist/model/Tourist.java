package com.soa.tourist.model;

import jakarta.persistence.*;

@Entity
public class Tourist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String visitedDestination;
	private String contactNumber;

	public Tourist() {
	}

	public Tourist(String name, String email, String visitedDestination, String contactNumber) {
		this.name = name;
		this.email = email;
		this.visitedDestination = visitedDestination;
		this.contactNumber = contactNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVisitedDestination() {
		return visitedDestination;
	}

	public void setVisitedDestination(String v) {
		visitedDestination = v;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String c) {
		contactNumber = c;
	}
}
