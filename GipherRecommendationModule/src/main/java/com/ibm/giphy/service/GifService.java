package com.ibm.giphy.service;

import java.util.List;
import java.util.Optional;
import com.ibm.giphy.model.Gif;

public interface GifService {
	public Gif addToRecommendations(Gif gif);
	public Optional<Gif> findById(String id);
	public List<Gif> fetchAll();
	public boolean deleteById(String id);
}
