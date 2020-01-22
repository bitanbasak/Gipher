package com.ibm.giphy.service;

import static org.junit.Assert.assertEquals;
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
import com.ibm.giphy.model.Favourite;
import com.ibm.giphy.repository.FavouriteRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FavouritesServiceImplTest {
	@Mock
	private FavouriteRepository favouritesRepository;
	@InjectMocks
	private FavouriteServiceImpl service;
	Favourite favourites;
	List<Favourite> listFavourites;
	Optional<Favourite> optFavourite;

	@Before
	public void setUp() throws Exception {
		favourites = new Favourite("cniCpOSDrSF6nE0vGx", "ctvcomedy", "gipher@gmail.com",
				"Excited Daily Show GIF by CTV Comedy Channel",
				"https://media1.giphy.com/media/cniCpOSDrSF6nE0vGx/giphy.gif?cid=be1a049434fee4c13ea94935ce92928cd1ac8b4f40a9e034&rid=giphy.gif",
				0);

		optFavourite = Optional.of(favourites);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveFavouriteSuccess() {
		when(favouritesRepository.save(Mockito.any(Favourite.class))).thenReturn(favourites);
		Favourite addedBook = service.saveFavourite(favourites);
		verify(favouritesRepository).save(Mockito.any());
	}

	@Test
	public void testGetFavouritesByIdSuccess() {
		when(favouritesRepository.findByEmailId((Mockito.anyString()))).thenReturn(listFavourites);
	}
}
