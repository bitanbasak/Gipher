package com.ibm.giphy.controller;

import java.util.List;

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

import com.ibm.giphy.exception.GifNotFoundException;
import com.ibm.giphy.model.Favourite;
import com.ibm.giphy.service.FavouriteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/favourites")
public class FavouriteController {

	private static Logger logger = LoggerFactory.getLogger(FavouriteController.class);

	@Autowired
	private FavouriteService favouriteService;

	@PostMapping("/addToFavourites")
	public ResponseEntity<?> saveGifToFavourites(@RequestBody Favourite f) {
		ResponseEntity<?> response = null;
		try {
			Favourite fv = favouriteService.saveFavourite(f);
			logger.info("inside the favourite gif try block");
			if (fv != null) {
				logger.info("the selected gif has been added to the favourites section");
				return new ResponseEntity<Favourite>(fv, HttpStatus.CREATED);
			} else {
				logger.info("the selected gif already exists in the favourites section");
				response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			logger.debug("there has been some conflict while adding a gif to favourites section");
			response = ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return response;
	}

	@GetMapping("/getAllFavourites")
	public ResponseEntity<List<Favourite>> getAllFavourites() {
		ResponseEntity<List<Favourite>> response = null;
		logger.info("inside the getAllFavourites method");
		List<Favourite> list = favouriteService.getAllFavourites();
		response = ResponseEntity.status(HttpStatus.OK).body(list);
		logger.info("all the favourite gifs has been fetched successfully");
		return response;
	}

	@GetMapping("/getAllFavouritesByEmailId/{emailId}")
	public ResponseEntity<List<Favourite>> getAllFavouritesByEmailId(@PathVariable("emailId") String emailId) {
		logger.info("inside getAllFavouritesByEmailId method");
		List<Favourite> list = favouriteService.getFavouritesByEmailId(emailId);
		logger.info("all the favourite gifs by the given emailId has been fetched successfully");
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@DeleteMapping("/deleteFromFavourites/{id}")
	public ResponseEntity<?> deleteFromFavourites(@PathVariable("id") String id) throws GifNotFoundException {
		logger.info("inside deleteFromFavourites method");
		ResponseEntity<?> response = null;
		favouriteService.deleteFromFavourites(id);
		response = ResponseEntity.status(HttpStatus.OK).build();
		logger.info("the favourite has been deleted by the given gifId");
		return response;
	}
}
