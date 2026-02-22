package com.harshkumar0614jain.ems.repository;

import com.harshkumar0614jain.ems.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    boolean existsByUserName(String userName);

    boolean existsByEmail( String email);
}
