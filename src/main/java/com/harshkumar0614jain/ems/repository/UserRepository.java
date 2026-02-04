package com.harshkumar0614jain.ems.repository;

import com.harshkumar0614jain.ems.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
