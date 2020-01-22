package com.ibm.giphy.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.giphy.model.Comment;
import com.ibm.giphy.repository.CommentRespository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRespository commentRepository;

	@Override
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getAllCommentByGifId(String gifId) {
		List<Comment> comments = commentRepository.findByGifId(gifId);
		return comments;
	}

	public Optional<Comment> getCommentById(String id) {
		Optional<Comment> comment = commentRepository.findById(id);
		return comment;
	}

	@Override
	public Comment deleteComment(String id) {
		commentRepository.deleteById(id);
		return null;
	}

	@Override
	public List<Comment> fetchByUserEmail(String userEmail) {
		List<Comment> commentList = commentRepository.findByUserEmail(userEmail);
		return commentList;
	}
}
