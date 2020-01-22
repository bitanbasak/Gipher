package com.ibm.giphy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.giphy.model.Comment;

public interface CommentRespository extends MongoRepository<Comment, String> {
	public List<Comment> findByGifId(String gifId);
	public Optional<Comment> findById(String id);
	public List<Comment> findByUserEmail(String userEmail);
}
