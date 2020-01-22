package com.ibm.giphy.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.giphy.model.Favourite;
@Repository
public interface FavouriteRepository extends MongoRepository<Favourite, String> {

	public List<Favourite> findByEmailId(String emailId);
}