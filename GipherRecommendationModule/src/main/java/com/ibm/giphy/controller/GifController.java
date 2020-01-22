package com.ibm.giphy.controller;

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
import com.ibm.giphy.model.Gif;
import com.ibm.giphy.service.GifService;

@CrossOrigin("*")
@RestController
@RequestMapping("/recommendation")

public class GifController {

	private Logger logger = LoggerFactory.getLogger(GifController.class);
	@Autowired
	private GifService gifService;

	@PostMapping("/addRecommendation")
	public ResponseEntity<?> addNewRecommendation(@RequestBody Gif gif) {
		logger.info("inside the addNewRecommendation method");
		Gif giphy = gifService.addToRecommendations(gif);
		if (giphy != null) {
			logger.info("added to recommendation");
			return new ResponseEntity<>(giphy, HttpStatus.OK);
		} else {
			logger.debug("insertion to recommendation failed");
			return new ResponseEntity<>("Insertion Failed", HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/fetchByGifId/{gifId}")
	public ResponseEntity<?> findbyGifId(@PathVariable String gifId) {
		logger.info("inside findByGifId method");
		Optional<Gif> gif = gifService.findById(gifId);
		if (gif.isPresent()) {
			logger.info("gif has been fetched by ID");
			return new ResponseEntity<>(gif.get(), HttpStatus.OK);
		}
		logger.info("gif with the ID does not exist");
		return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<Gif>> fetchAll() {
		logger.info("inside the fetchAll method");
		logger.info("all the recommendations has been fetched");
		return new ResponseEntity<>(gifService.fetchAll(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteByGifId/{gifId}")
	public ResponseEntity<String> deleteByGifId(@PathVariable String gifId) {
		logger.info("inside the deleteByGifId method");
		Optional<Gif> gif = gifService.findById(gifId);
		if (gif.isPresent()) {
			logger.info("gif with id has been fetched");
			if (gifService.deleteById(gifId)) {
				logger.info("gif with the id has been deleted");
				return new ResponseEntity<>("Successfully Deleted!", HttpStatus.OK);
			}
		}
		logger.info("failed to delete gif");
		return new ResponseEntity<>("Failed to Deleted!", HttpStatus.CONFLICT);
	}

}