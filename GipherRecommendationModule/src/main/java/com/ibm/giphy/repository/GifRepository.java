package com.ibm.giphy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.giphy.model.Gif;

@Repository
public interface GifRepository extends MongoRepository<Gif, String> {
	public List<Gif> findAll();
	public void deleteById(String id);
}
