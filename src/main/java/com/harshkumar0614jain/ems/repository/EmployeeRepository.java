package com.harshkumar0614jain.ems.repository;

import com.harshkumar0614jain.ems.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByUserId (String userId);
}
