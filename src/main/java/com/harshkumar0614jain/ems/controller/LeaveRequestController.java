package com.harshkumar0614jain.ems.controller;

import com.harshkumar0614jain.ems.enums.Role;
import com.harshkumar0614jain.ems.modal.LeaveRequestModel;
import com.harshkumar0614jain.ems.modal.LeaveResponseModel;
import com.harshkumar0614jain.ems.modal.ResponseModel;
import com.harshkumar0614jain.ems.service.LeaveRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping
    public ResponseEntity<ResponseModel<List<LeaveResponseModel>>> getAllLeaveRequests(
            @RequestParam Role role,
            @RequestParam(required = false) String employeeId) {

        List<LeaveResponseModel> list = leaveRequestService.findAllLeaveRequest(role,employeeId);
        ResponseModel<List<LeaveResponseModel>> response = new ResponseModel<>("List retrieved successfully", list);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<ResponseModel<LeaveResponseModel>> getLeaveRequest(@PathVariable String leaveId) {
        LeaveResponseModel leaveDetails = leaveRequestService.getLeaveDetails(leaveId);
        ResponseModel<LeaveResponseModel> response = new ResponseModel<>("Leave details is fetched successfully", leaveDetails);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel<LeaveResponseModel>> createLeaveRequest(@Valid @RequestBody LeaveRequestModel leaveRequestModel){
        LeaveResponseModel leaveResponseModel = leaveRequestService.createLeaveRequest(leaveRequestModel);
        ResponseModel<LeaveResponseModel> response = new ResponseModel<>("Leave Request Created Successfully", leaveResponseModel );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
