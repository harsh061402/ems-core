package com.harshkumar0614jain.ems.service;

import com.harshkumar0614jain.ems.entity.LeaveRequest;
import com.harshkumar0614jain.ems.enums.LeaveStatus;
import com.harshkumar0614jain.ems.enums.Role;
import com.harshkumar0614jain.ems.exception.ResourceNotFoundException;
import com.harshkumar0614jain.ems.model.LeaveDecisionRequestModel;
import com.harshkumar0614jain.ems.model.LeaveRequestModel;
import com.harshkumar0614jain.ems.model.LeaveResponseModel;
import com.harshkumar0614jain.ems.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;


    public List<LeaveResponseModel> findAllLeaveRequest(Role role, String employeeId) {

    if (role == Role.ADMIN) {
        return leaveRequestRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    if(role==Role.EMPLOYEE && employeeId==null){
        throw new IllegalArgumentException("Employee Id is null");
    }

    if(role == Role.EMPLOYEE){
        return leaveRequestRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
        }
     throw new IllegalArgumentException("Invalid role");
    }


    public LeaveResponseModel createLeaveRequest(LeaveRequestModel requestModel){

        LocalDate startDate = requestModel.getStartDate();
        LocalDate endDate = requestModel.getEndDate();

        int leaveDays = endDate.compareTo(startDate);
        if (leaveDays < 0) {
            throw new IllegalArgumentException("Start date should be before End date");
        }

//      if employee have to apply the leave he has to enough balance to applied it
//        if (leaveDays < remainingBalance) {
//            throw new IllegalArgumentException("Apply leave days should be less than remaining Balance");
//        }

        LeaveRequest leaveRequest = LeaveRequest.builder()
                .title(requestModel.getTitle())
                .description(requestModel.getDescription())
                .employeeId(requestModel.getEmployeeId())
                .startDate(requestModel.getStartDate())
                .endDate(requestModel.getEndDate())
                .leaveType(requestModel.getLeaveType())
                .leaveStatus(LeaveStatus.PENDING)
                .build();

        LeaveRequest response = leaveRequestRepository.save(leaveRequest);

        return mapToResponse(response);
    }

    public LeaveResponseModel getLeaveDetails(String leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(()-> new ResourceNotFoundException("leaveRequestId",
                        "Leave request not found of id :"+ leaveRequestId));
        return mapToResponse(leaveRequest);
    }

    private LeaveResponseModel mapToResponse(LeaveRequest leaveRequest) {
        return   LeaveResponseModel.builder()
                .id(leaveRequest.getId())
                .tittle(leaveRequest.getTitle())
                .description(leaveRequest.getDescription())
                .employeeId(leaveRequest.getEmployeeId())
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .leaveType(leaveRequest.getLeaveType())
                .leaveStatus(leaveRequest.getLeaveStatus())
                .createdAt(leaveRequest.getCreatedAt())
                .updatedAt(leaveRequest.getUpdatedAt())
                .managerComment(leaveRequest.getManagerComment())
                .build();

    }

    public LeaveResponseModel decideLeaveRequest(String leaveRequestId,
                                                 LeaveDecisionRequestModel requestModel) {

        if(requestModel.getLeaveStatus().equals(LeaveStatus.PENDING)){
            throw new IllegalArgumentException("Leave Status is already PENDING");
        }

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("leaveRequestId",
                        "Leave request not found of id :"+ leaveRequestId));

        leaveRequest.setLeaveStatus(requestModel.getLeaveStatus());
        leaveRequest.setManagerComment(requestModel.getManagerComment());

        leaveRequestRepository.save(leaveRequest);
        return mapToResponse(leaveRequest);
    }
}
