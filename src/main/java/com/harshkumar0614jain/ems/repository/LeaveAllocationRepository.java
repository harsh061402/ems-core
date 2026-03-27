package com.harshkumar0614jain.ems.repository;

import com.harshkumar0614jain.ems.entity.LeaveAllocation;
import com.harshkumar0614jain.ems.enums.LeaveType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveAllocationRepository extends MongoRepository<LeaveAllocation,String> {
    List<LeaveAllocation> findByEmployeeId(String employeeId);

    Optional<LeaveAllocation> findByEmployeeIdAndLeaveTypeAndYear(
            String employeeId, LeaveType leaveType, int year);
    boolean existsByEmployeeIdAndLeaveTypeAndYear(
            String employeeId, LeaveType leaveType, int year);
}
