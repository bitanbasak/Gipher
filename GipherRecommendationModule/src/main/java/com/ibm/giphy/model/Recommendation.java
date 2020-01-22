package com.ibm.giphy.model;

import java.util.List;

public class Recommendation {
	private int count;
	private List<String> userEmail;

	public Recommendation() {
	}

	public Recommendation(int count, List<String> userEmail) {
		super();
		this.count = count;
		this.userEmail = userEmail;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(List<String> userEmail) {
		this.userEmail = userEmail;
	}
}
