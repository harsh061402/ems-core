package com.harshkumar0614jain.ems.controller;

import com.harshkumar0614jain.ems.model.LeaveDecisionRequestModel;
import com.harshkumar0614jain.ems.model.LeaveRequestModel;
import com.harshkumar0614jain.ems.model.LeaveResponseModel;
import com.harshkumar0614jain.ems.model.ResponseModel;
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

    @PostMapping
    public ResponseEntity<ResponseModel<LeaveResponseModel>> createLeaveRequest(
            @Valid @RequestBody LeaveRequestModel requestModel){

        LeaveResponseModel leaveResponseModel = leaveRequestService
                .applyLeave(requestModel);

        ResponseModel<LeaveResponseModel> response = new ResponseModel<>(
                "Leave Request Created Successfully", leaveResponseModel );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseModel<List<LeaveResponseModel>>> getAllLeaveRequests() {

        List<LeaveResponseModel> list = leaveRequestService
                .findAllLeaveRequest();

        ResponseModel<List<LeaveResponseModel>> response = new ResponseModel<>(
                "List retrieved successfully", list);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/{leaveRequestId}")
    public ResponseEntity<ResponseModel<LeaveResponseModel>> getLeaveRequest(
            @PathVariable String leaveRequestId) {
        LeaveResponseModel leaveDetails = leaveRequestService
                .getLeaveDetailsById(leaveRequestId);

        ResponseModel<LeaveResponseModel> response = new ResponseModel<>(
                "Leave details is fetched successfully", leaveDetails);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PatchMapping("/{leaveRequestId}/decision")
    public ResponseEntity<ResponseModel<LeaveResponseModel>> leaveRequestDecision(
            @PathVariable String leaveRequestId,
            @RequestBody @Valid LeaveDecisionRequestModel requestModel) {

        LeaveResponseModel updatedLeave = leaveRequestService
                .approveOrRejectLeave(leaveRequestId, requestModel);

        ResponseModel<LeaveResponseModel> response = new ResponseModel<>(
                "Leave Request is updated",updatedLeave);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
