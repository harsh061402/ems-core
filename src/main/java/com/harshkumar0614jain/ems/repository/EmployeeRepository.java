package com.harshkumar0614jain.ems.repository;

import com.harshkumar0614jain.ems.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
