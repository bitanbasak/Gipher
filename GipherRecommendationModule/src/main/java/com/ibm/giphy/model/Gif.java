package com.ibm.giphy.model;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recommendations")
public class Gif {
	@Id
	private String id;
	private String imageURL;
	private int isSticker;
	private List<String> recommended;
	private String title;
	private String username;

	public Gif() {
		super();
	}

	public Gif(String id, String imageURL, int isSticker, List<String> recommended, String title, String username) {
		super();
		this.id = id;
		this.imageURL = imageURL;
		this.isSticker = isSticker;
		this.recommended = recommended;
		this.title = title;
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public int getIsSticker() {
		return isSticker;
	}

	public List<String> getRecommended() {
		return recommended;
	}

	public String getTitle() {
		return title;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public void setIsSticker(int isSticker) {
		this.isSticker = isSticker;
	}

	public void setRecommended(List<String> recommended) {
		this.recommended = recommended;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
