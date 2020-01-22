package com.ibm.giphy.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.giphy.model.Comment;
import com.ibm.giphy.service.CommentService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CommentController.class)

public class CommentControllerTest {

	// Sending request to controller
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CommentService service;
	private Comment comment;
	private List<Comment> listComment;
	private Optional<Comment> optComment;

	@Before
	public void setUp() throws Exception {
		comment = new Comment();
		comment.set_id("111");
		comment.setCreationDate(null);
		comment.setContent("Sample Comment");
		comment.setGifId("gifid111");
		comment.setUserEmail("gipher@gmail.com");
		comment.setLikedBy(null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddToCommentsSuccess() throws Exception {
		// setup the service mock
		when(service.addComment(Mockito.any(Comment.class))).thenReturn(comment);
		// send a post request using mockMVC
		String commentJson = new ObjectMapper().writeValueAsString(comment);
		mockMvc.perform(post("/comment/addNewComment").contentType(MediaType.APPLICATION_JSON).content(commentJson))
				.andExpect(status().isCreated());
		// verify mock has been called
		verify(service).addComment(Mockito.any(Comment.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testAddToCommentsFailure() throws Exception {
		// setup the service mock
		when(service.addComment(Mockito.any(Comment.class))).thenReturn(null);
		// send a post request using mockMVC
		String commentJson = new ObjectMapper().writeValueAsString(comment);
		mockMvc.perform(post("/comment/addNewComment").contentType(MediaType.APPLICATION_JSON).content(commentJson))
				.andExpect(status().isConflict());
		// verify mock has been called
		verify(service).addComment(Mockito.any(Comment.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testDeleteCommentSuccess() throws Exception {
		doReturn(comment).when(service).deleteComment(Mockito.anyString());
		mockMvc.perform(delete("/comment/deleteComment/111")).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).deleteComment(Mockito.anyString());
	}

	@Test
	public void testDeleteCommentFailure() throws Exception {
		doReturn(null).when(service).deleteComment(Mockito.anyString());
		mockMvc.perform(delete("/comment/deleteComment/11")).andExpect(status().isConflict()).andDo(print());
	}

	@Test
	public void testGetAllCommentsByGifIdSuccess() throws Exception {
		when(service.getAllCommentByGifId(Mockito.anyString())).thenReturn(listComment);
		mockMvc.perform(get("/comment/getCommentsByGifId/gifid111")).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).getAllCommentByGifId(Mockito.anyString());
	}

	@Test
	public void testGetAllCommentsByGifIdFailure() throws Exception {
		when(service.getAllCommentByGifId(Mockito.anyString())).thenReturn(listComment);
		mockMvc.perform(get("/comment/getCommentsByGifId/gifid11")).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).getAllCommentByGifId(Mockito.anyString());
	}

	@Test
	public void testGetAllCommentsByIdSuccess() throws Exception {
		when(service.getCommentById(Mockito.anyString())).thenReturn(optComment);
		mockMvc.perform(get("/comment/getCommentsById/111")).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).getCommentById(Mockito.anyString());
	}

	@Test
	public void testGetAllCommentsByUserEmailSuccess() throws Exception {
		when(service.fetchByUserEmail(Mockito.anyString())).thenReturn(listComment);
		mockMvc.perform(get(
				"/comment/getCommentsByUserEmail/eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkaW1wbGVAZ21haWwuY29tIiwic3ViIjoiRGltcGxlIiwiaWF0IjoxNTc5MTgxNzUzfQ.DQdqZ-U-ODniOKxozrAYKH5_1veOdLWSZsXFqJ6_c0Q"))
				.andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).fetchByUserEmail(Mockito.anyString());
	}
}
