package com.harshkumar0614jain.ems.modal;

import com.harshkumar0614jain.ems.enums.LeaveStatus;
import com.harshkumar0614jain.ems.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveResponseModel {
    private String id;
    private String tittle;
    private String description;
    private String empId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;
}
