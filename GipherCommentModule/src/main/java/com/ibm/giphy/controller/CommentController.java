package com.ibm.giphy.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.giphy.model.Comment;
import com.ibm.giphy.service.CommentService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@CrossOrigin("*")
@RestController
@RequestMapping("/comment")
public class CommentController {

	private Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	private CommentService commentService;

	@PostMapping("/addNewComment")
	public ResponseEntity<?> addNewComment(@RequestBody Comment comment) {
		logger.info("inside the addNewComment");
		try {
			logger.info("inside the addNewComment try block");
			comment.setCreationDate(LocalDateTime.now());
			logger.info("Creation date has been set");
			Comment comments = commentService.addComment(comment);
			logger.info("comment has been added");
			return new ResponseEntity<>(comments, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.debug("failed to add comment");
			logger.error(e.getMessage());
		}
		logger.info("failed to add comment");
		return new ResponseEntity<>("Failed to save comment", HttpStatus.CONFLICT);
	}

	@GetMapping("/getCommentsByGifId/{gifId}")
	public ResponseEntity<List<Comment>> getCommentsByGifId(@PathVariable String gifId) {
		logger.info("inside the getCommentByGifId");
		List<Comment> commentsList = commentService.getAllCommentByGifId(gifId);
		logger.info("comments by Gif Id has been fetched");
		return new ResponseEntity<>(commentsList, HttpStatus.OK);
	}

	@GetMapping("/getCommentsById/{id}")
	public ResponseEntity<Comment> getCommentsById(@PathVariable String id) {
		logger.info("inside the getCommentsById");
		Optional<Comment> comment = commentService.getCommentById(id);
		logger.info("comment with the id has been fetched");
		return new ResponseEntity<>(comment.get(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteComment/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable String id) {
		logger.info("inside the deleteComment method");
		commentService.deleteComment(id);
		logger.info("comment has been deleted");
		return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
	}

	@GetMapping("/getCommentsByUserEmail/{token}")
	public ResponseEntity<List<Comment>> getCommentsUserEmail(@PathVariable String token) {
		logger.info("inside the getCommentsByUserEmail method");
		Claims claim = Jwts.parser().setSigningKey("usersecretkey").parseClaimsJws(token).getBody();
		logger.info("claim has been set");
		return new ResponseEntity<>(commentService.fetchByUserEmail(claim.getId()), HttpStatus.OK);
	}
}