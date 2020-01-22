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
import java.util.List;
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
import com.ibm.giphy.model.Favourite;
import com.ibm.giphy.service.FavouriteService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FavouriteController.class)

public class FavouriteControllerTest {

	// Sending request to controller
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FavouriteService service;
	private Favourite favourites;
	private List<Favourite> listFavourites;

	@Before
	public void setUp() throws Exception {
		favourites = new Favourite("cniCpOSDrSF6nE0vGx", "ctvcomedy", "gipher@gmail.com",
				"Excited Daily Show GIF by CTV Comedy Channel",
				"https://media1.giphy.com/media/cniCpOSDrSF6nE0vGx/giphy.gif?cid=be1a049434fee4c13ea94935ce92928cd1ac8b4f40a9e034&rid=giphy.gif",
				0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddToFavouritesSuccess() throws Exception {
		// setup the service mock
		when(service.saveFavourite(Mockito.any(Favourite.class))).thenReturn(favourites);
		// send a post request using mockMVC
		String favouritesJson = new ObjectMapper().writeValueAsString(favourites);
		mockMvc.perform(
				post("/favourites/addToFavourites").contentType(MediaType.APPLICATION_JSON).content(favouritesJson))
				.andExpect(status().isCreated());
		// verify mock has been called
		verify(service).saveFavourite(Mockito.any(Favourite.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testAddToFavouritesFailure() throws Exception {
		// setup the service mock
		when(service.saveFavourite(Mockito.any(Favourite.class))).thenReturn(null);
		// send a post request using mockMVC
		String favouritesJson = new ObjectMapper().writeValueAsString(favourites);
		mockMvc.perform(
				post("/favourites/addToFavourites").contentType(MediaType.APPLICATION_JSON).content(favouritesJson))
				.andExpect(status().isInternalServerError());
		// verify mock has been called
		verify(service).saveFavourite(Mockito.any(Favourite.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetAllFavouritesByEmailIdSuccess() throws Exception {
		when(service.getFavouritesByEmailId(Mockito.anyString())).thenReturn(listFavourites);
		mockMvc.perform(get("/favourites/getAllFavouritesByEmailId/gipher@gmail.com")).andExpect(status().isOk())
				.andDo(print());
		verify(service, times(1)).getFavouritesByEmailId(Mockito.anyString());
	}

	@Test
	public void testDeleteFromFavouritesSuccess() throws Exception {
		when(service.deleteFromFavourites(Mockito.anyString())).thenReturn(favourites);
		mockMvc.perform(delete("/favourites/deleteFromFavourites/cniCpOSDrSF6nE0vGx")).andExpect(status().isOk())
				.andDo(print());
		verify(service, times(1)).deleteFromFavourites(Mockito.anyString());
	}

}
