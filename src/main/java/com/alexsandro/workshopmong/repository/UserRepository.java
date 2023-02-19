package com.alexsandro.workshopmong.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alexsandro.workshopmong.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	//User findOne(String id);
	
	

}
