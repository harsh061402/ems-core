package com.harshkumar0614jain.ems.service;

import com.harshkumar0614jain.ems.entity.LeaveRequest;
import com.harshkumar0614jain.ems.enums.LeaveStatus;
import com.harshkumar0614jain.ems.enums.Role;
import com.harshkumar0614jain.ems.modal.LeaveRequestModel;
import com.harshkumar0614jain.ems.modal.LeaveResponseModel;
import com.harshkumar0614jain.ems.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    if(role == Role.EMPLOYEE){
        return leaveRequestRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
        }
     throw new IllegalArgumentException("Invalid role");
    }


    public LeaveResponseModel createLeaveRequest(LeaveRequestModel leaveRequestModel){

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
        return leaveResponseModel;
    }

    public LeaveResponseModel getLeaveDetails(String leaveId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
                .orElseThrow(()-> new RuntimeException("Leave request not found"));
        return mapToResponse(leaveRequest);
    }
}
