package com.ibm.giphy.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.ibm.giphy.exception.UserAlreadyExistsException;
import com.ibm.giphy.model.User;
import com.ibm.giphy.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)

public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl service;
	User user;
	Optional<User> optUser;

	@Before
	public void setUp() throws Exception {
		user = new User("giphy@gmail.com", "Gipher", "Gipher", null, "female", "0987654321", "www.images.com");
		optUser = Optional.of(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterUserSuccess() throws UserAlreadyExistsException {
		when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		User addedUser = service.registerUser(user);
		assertEquals(user.getEmailId(), addedUser.getEmailId());
		verify(userRepository).save(Mockito.any());
	}

	@Test
	public void testRegisterUserFailure() throws UserAlreadyExistsException {
		when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(userRepository.save(Mockito.any(User.class))).thenReturn(null);
	}

	@Test
	public void testUpdateUserSuccess() throws UserAlreadyExistsException {
		when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
	}

	@Test
	public void testUpdateUserFailure() throws UserAlreadyExistsException {
		when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(userRepository.save(Mockito.any(User.class))).thenReturn(null);
	}

	@Test
	public void testAuthenticateUserSuccess() {
		when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
	}

	@Test
	public void testAuthenticateUserFailure() {
		when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(userRepository.save(Mockito.any(User.class))).thenReturn(null);
	}

}
