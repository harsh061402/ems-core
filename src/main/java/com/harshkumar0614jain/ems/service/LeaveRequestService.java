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
        throw new ResourceNotFoundException("Employee Id is null");
    }

    if(role == Role.EMPLOYEE){
        return leaveRequestRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
        }
     throw new IllegalArgumentException("Invalid role");
    }


    public LeaveResponseModel createLeaveRequest(LeaveRequestModel leaveRequestModel){

        LocalDate startDate = leaveRequestModel.getStartDate();
        LocalDate endDate = leaveRequestModel.getEndDate();

        int leaveDays = endDate.compareTo(startDate);
        if (leaveDays < 0) {
            throw new IllegalArgumentException("Start date should be before End date");
        }

//      if employee have to apply the leave he has to enough balance to applied it
//        if (leaveDays < remainingBalance) {
//            throw new IllegalArgumentException("Apply leave days should be less than remaining Balance");
//        }

        LeaveRequest leaveRequest = new LeaveRequest();

        leaveRequest.setTitle(leaveRequestModel.getTitle());
        leaveRequest.setDescription(leaveRequestModel.getDescription());
        leaveRequest.setEmployeeId(leaveRequestModel.getEmployeeId());
        leaveRequest.setStartDate(leaveRequestModel.getStartDate());
        leaveRequest.setEndDate(leaveRequestModel.getEndDate());
        leaveRequest.setLeaveType(leaveRequestModel.getLeaveType());
        leaveRequest.setLeaveStatus(LeaveStatus.PENDING);

        LeaveRequest response = leaveRequestRepository.save(leaveRequest);

        return mapToResponse(response);
    }

    public LeaveResponseModel getLeaveDetails(String leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(()-> new ResourceNotFoundException("Leave request not found of id :"+ leaveRequestId));
        return mapToResponse(leaveRequest);
    }

    private LeaveResponseModel mapToResponse(LeaveRequest leaveRequest) {
        LeaveResponseModel leaveResponseModel = new LeaveResponseModel();
        leaveResponseModel.setId(leaveRequest.getId());
        leaveResponseModel.setTittle(leaveRequest.getTitle());
        leaveResponseModel.setDescription(leaveRequest.getDescription());
        leaveResponseModel.setEmpId(leaveRequest.getEmployeeId());
        leaveResponseModel.setStartDate(leaveRequest.getStartDate());
        leaveResponseModel.setEndDate(leaveRequest.getEndDate());
        leaveResponseModel.setLeaveType(leaveRequest.getLeaveType());
        leaveResponseModel.setLeaveStatus(leaveRequest.getLeaveStatus());
        leaveResponseModel.setCreatedAt(leaveRequest.getCreatedAt());
        leaveResponseModel.setUpdatedAt(leaveRequest.getUpdatedAt());
        leaveResponseModel.setManagerComment(leaveRequest.getManagerComment());
        return leaveResponseModel;
    }

    public LeaveResponseModel decideLeaveRequest(String leaveRequestId,
                                                 LeaveDecisionRequestModel decisionRequestModel) {

        if(decisionRequestModel.getLeaveStatus().equals(LeaveStatus.PENDING)){
            throw new IllegalArgumentException("Leave Status is already PENDING");
        }

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found of id :"+ leaveRequestId));

        leaveRequest.setLeaveStatus(decisionRequestModel.getLeaveStatus());
        leaveRequest.setManagerComment(decisionRequestModel.getManagerComment());

        leaveRequestRepository.save(leaveRequest);
        return mapToResponse(leaveRequest);
    }
}
