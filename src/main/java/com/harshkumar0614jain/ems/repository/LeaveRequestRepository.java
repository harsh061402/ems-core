package com.harshkumar0614jain.ems.repository;

import com.harshkumar0614jain.ems.entity.LeaveRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LeaveRequestRepository extends MongoRepository<LeaveRequest,String> {

//    List<LeaveRequest> findByEmployeeId(@Valid @PathVariable("employeeId") String employeeId);
    List<LeaveRequest> findByEmployeeId(String employeeId);

}
