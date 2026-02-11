package com.harshkumar0614jain.ems.model;

import com.harshkumar0614jain.ems.enums.LeaveType;
import lombok.Data;

@Data
public class LeaveBalance {

    private LeaveType leaveType;
    private int totalAllocated;
    private int usedLeave;

    private int remainingLeave(){
        return totalAllocated - usedLeave;
    }
}
