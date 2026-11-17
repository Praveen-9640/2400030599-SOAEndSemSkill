package com.soa.tourist.dto;

public class TouristUpdateRequest {
	private String name, email, visitedDestination, contactNumber;

	public TouristUpdateRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String v) {
		name = v;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		email = v;
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

	public void setContactNumber(String v) {
		contactNumber = v;
	}
}
