package com.ibm.giphy.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import com.ibm.giphy.controller.UserController;
import com.ibm.giphy.exception.UserAlreadyExistsException;
import com.ibm.giphy.model.User;
import com.ibm.giphy.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)

public class userControllerTest {

	// Sending request to controller
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;
	private User user;
	Optional<User> optUser;

	@Before
	public void setUp() throws Exception {
		user = new User("giphy@gmail.com", "Gipher", "Gipher", null, "female", "0987654321", "www.images.com");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = Exception.class)
	public void testGetUserFailure() throws Exception {
		// When a call is given to service.getBook
		when(service.getUserByEmail(Mockito.anyString())).thenThrow(Exception.class);
		mockMvc.perform(get("/user/findUserByEmail/gipherrr@gmail.com")).andExpect(status().isConflict())
				.andDo(print());
		verify(service).getByEmail(Mockito.anyString());
	}

	@Test
	public void testAddUserSuccess() throws Exception {
		// setup the service mock
		when(service.registerUser(Mockito.any(User.class))).thenReturn(user);
		// send a post request using mockMVC
		String bookJson = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/user/addNewUser").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isCreated());
		// verify mock has been called
		verify(service).registerUser(Mockito.any(User.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testAddUserFailure() throws Exception {
		// setup the service mock
		when(service.registerUser(Mockito.any(User.class))).thenThrow(UserAlreadyExistsException.class);
		// send a post request using mockMVC
		String bookJson = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/user/addNewUser").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isConflict());
		// verify mock has been called
		verify(service).registerUser(Mockito.any(User.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testLoginUserSuccess() throws Exception {
		// setup the service mock
		when(service.authenticateUser(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
		// send a post request using mockMVC
		String bookJson = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/user/loginUser").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isOk());
		// verify mock has been called
		verify(service).authenticateUser(Mockito.anyString(), Mockito.anyString());
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testLoginUserFailure() throws Exception {
		// setup the service mock
		when(service.authenticateUser(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		// send a post request using mockMVC
		String bookJson = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/user/loginUser").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isUnauthorized());
		// verify mock has been called
		verify(service).authenticateUser(Mockito.anyString(), Mockito.anyString());
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testUpdateUserSuccess() throws Exception {
		// setup the service mock
		when(service.updateUser(Mockito.any(User.class))).thenReturn(user);
		// send a post request using mockMVC
		String bookJson = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(put("/user/updateUser").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isOk());
	}
}
