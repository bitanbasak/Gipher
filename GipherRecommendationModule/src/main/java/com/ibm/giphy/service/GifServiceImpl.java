package com.ibm.giphy.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.giphy.model.Gif;
import com.ibm.giphy.repository.GifRepository;

@Service
public class GifServiceImpl implements GifService {

	@Autowired
	GifRepository gifRepo;

	@Override
	public Gif addToRecommendations(Gif gif) {
		return gifRepo.save(gif);
	}

	@Override
	public Optional<Gif> findById(String id) {
		Optional<Gif> gif = gifRepo.findById(id);
		return gif;
	}

	@Override
	public List<Gif> fetchAll() {
		List<Gif> gifs = gifRepo.findAll();
		return gifs;
	}

	public boolean deleteById(String id) {
		gifRepo.deleteById(id);
		Optional<Gif> gif = gifRepo.findById(id);
		if (gif.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

}
