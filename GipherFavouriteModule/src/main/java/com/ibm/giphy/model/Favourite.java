package com.ibm.giphy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favourites")
public class Favourite {

	@Id
	private String favId;
	private String username;
	private String emailId;
	private String title;
	private String imageURL;
	private int isSticker;
	
	public Favourite() {
		super();
	}

	public Favourite(String favId, String username, String emailId, String title, String imageURL, int isSticker) {
		super();
		this.favId = favId;
		this.username = username;
		this.emailId = emailId;
		this.title = title;
		this.imageURL = imageURL;
		this.isSticker = isSticker;
	}





	public String getFavId() {
		return favId;
	}


	public void setFavId(String favId) {
		this.favId = favId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getIsSticker() {
		return isSticker;
	}

	public void setIsSticker(int isSticker) {
		this.isSticker = isSticker;
	}
}