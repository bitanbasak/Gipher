package com.ibm.giphy.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.ibm.giphy.model.Comment;
import com.ibm.giphy.repository.CommentRespository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CommentServiceImplTest {

	@Mock
	private CommentRespository commentRepository;
	@InjectMocks
	private CommentServiceImpl service;
	Comment comment;
	List<Comment> listComment;
	Optional<Comment> optComment;

	@Before
	public void setUp() throws Exception {

		comment = new Comment();
		comment.set_id("111");
		comment.setCreationDate(null);
		comment.setContent("Sample Comment");
		comment.setGifId("gifid111");
		comment.setUserEmail("gipher@gmail.com");
		comment.setLikedBy(null);

		optComment = Optional.of(comment);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveCommentSuccess() {
		when(commentRepository.save((Mockito.any(Comment.class)))).thenReturn(comment);
		Comment addedBook = service.addComment(comment);
		verify(commentRepository).save(Mockito.any());
	}

	@Test
	public void testGetAllCommentsByGifIdSuccess() {
		when(commentRepository.findByGifId((Mockito.any()))).thenReturn(listComment);
	}

	@Test
	public void testGetCommentByIdSuccess() {
		when(commentRepository.findById((Mockito.any()))).thenReturn(optComment);
	}
}
