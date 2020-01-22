package com.ibm.giphy.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
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
import com.ibm.giphy.model.Gif;
import com.ibm.giphy.service.GifService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GifController.class)

public class RecommendationControllerTest {

	// Sending request to controller
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GifService service;
	private Gif gif;
	private Optional<Gif> optGif;
	private List<Gif> listGif;

	@Before
	public void setUp() throws Exception {
		gif = new Gif();
		gif.setId("111");
		gif.setImageURL("www.gifimage.com");
		gif.setIsSticker(0);
		gif.setRecommended(new ArrayList<>());
		gif.setTitle("Title1");
		gif.setUsername("User1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddRecommendationSuccess() throws Exception {
		// setup the service mock
		when(service.addToRecommendations(Mockito.any(Gif.class))).thenReturn(gif);
		// send a post request using mockMVC
		String recommendedJson = new ObjectMapper().writeValueAsString(gif);
		mockMvc.perform(post("/recommendation/addRecommendation").contentType(MediaType.APPLICATION_JSON)
				.content(recommendedJson)).andExpect(status().isOk());
		// verify mock has been called
		verify(service).addToRecommendations(Mockito.any(Gif.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testAddRecommendationFailure() throws Exception {
		// setup the service mock
		when(service.addToRecommendations(Mockito.any(Gif.class))).thenReturn(null);
		// send a post request using mockMVC
		String recommendedJson = new ObjectMapper().writeValueAsString(gif);
		mockMvc.perform(post("/recommendation/addRecommendation").contentType(MediaType.APPLICATION_JSON)
				.content(recommendedJson)).andExpect(status().isConflict());
		// verify mock has been called
		verify(service).addToRecommendations(Mockito.any(Gif.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testFetchByGifIdIdSuccess() throws Exception {
		when(service.findById(gif.getId())).thenReturn(optGif);
		mockMvc.perform(get("/recommendation/fetchByGifId/111")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testFetchAllRecommendationsSuccess() throws Exception {
		when(service.fetchAll()).thenReturn(listGif);
		mockMvc.perform(get("/recommendation/fetchAll")).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).fetchAll();
	}

	@Test
	public void testDeleteFromRecommendationsSuccess() throws Exception {
		when(service.deleteById(gif.getId())).thenReturn(true);
		mockMvc.perform(delete("/recommendation/deleteByGifId/{gifId}", gif.getId())).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void testDeleteFromRecommendationsFailure() throws Exception {
		when(service.deleteById(gif.getId())).thenReturn(true);
		mockMvc.perform(delete("/recommendation/deleteByGifId/11")).andExpect(status().isConflict()).andDo(print());
	}
}
