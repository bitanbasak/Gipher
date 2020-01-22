package com.ibm.giphy.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {

	@Id
	private String _id;
	private LocalDateTime creationDate;
	private String content;
	private String gifId;
	private String userEmail;
	private List<String> likedBy;

	public Comment(String _id, LocalDateTime creationDate, String content, String gifId, String userEmail,
			List<String> likedBy) {
		super();
		this._id = _id;
		this.creationDate = creationDate;
		this.content = content;
		this.gifId = gifId;
		this.userEmail = userEmail;
		this.likedBy = likedBy;
	}

	public List<String> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(List<String> likedBy) {
		this.likedBy = likedBy;
	}

	public Comment() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String id) {
		this._id = id;
	}

	public Comment(String userEmail) {
		super();
		this.userEmail = userEmail;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGifId() {
		return gifId;
	}

	public void setGifId(String gifId) {
		this.gifId = gifId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}