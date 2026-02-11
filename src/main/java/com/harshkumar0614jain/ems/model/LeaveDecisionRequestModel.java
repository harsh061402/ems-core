package com.harshkumar0614jain.ems.model;

import com.harshkumar0614jain.ems.enums.LeaveStatus;
import lombok.Data;

@Data
public class LeaveDecisionRequestModel {
    private LeaveStatus leaveStatus;
    private String managerComment;
}
