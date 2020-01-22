package com.ibm.giphy.service;

import java.util.List;
import java.util.Optional;
import com.ibm.giphy.model.Comment;

public interface CommentService {
	public Comment addComment(Comment comment);
	public List<Comment> getAllCommentByGifId(String gifId);
	public Optional<Comment> getCommentById(String id);
	public Comment deleteComment(String id);
	public List<Comment> fetchByUserEmail(String userEmail);
}
