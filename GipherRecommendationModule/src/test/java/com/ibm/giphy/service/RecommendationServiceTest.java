package com.ibm.giphy.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
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
import com.ibm.giphy.model.Gif;
import com.ibm.giphy.repository.GifRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RecommendationServiceTest {

	@Mock
	private GifRepository gifRepository;
	@InjectMocks
	private GifServiceImpl service;
	Gif gif;
	List<Gif> listGif;
	Optional<Gif> optGif;

	@Before
	public void setUp() throws Exception {
		gif = new Gif();
		gif.setId("111");
		gif.setImageURL("www.gifimage.com");
		gif.setIsSticker(0);
		gif.setRecommended(new ArrayList<>());
		gif.setTitle("Title1");
		gif.setUsername("User1");

		optGif = Optional.of(gif);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveRecommendationSuccess() {
		when(gifRepository.save((Mockito.any(Gif.class)))).thenReturn(gif);
		verify(gifRepository).save(Mockito.any());
	}

	@Test
	public void testSaveRecommendationFailure() {
		when(gifRepository.save((Mockito.any(Gif.class)))).thenReturn(null);
		verify(gifRepository).save(Mockito.any());
	}

	@Test
	public void testFindByIdSuccess() {
		when(gifRepository.findById((Mockito.any()))).thenReturn(optGif);
	}

	@Test
	public void testFindByIdFailure() {
		when(gifRepository.findById((Mockito.any()))).thenReturn(null);
	}
}
