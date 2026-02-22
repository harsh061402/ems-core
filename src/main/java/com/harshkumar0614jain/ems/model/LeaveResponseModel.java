package com.harshkumar0614jain.ems.model;

import com.harshkumar0614jain.ems.enums.LeaveStatus;
import com.harshkumar0614jain.ems.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveResponseModel {
    private String id;
    private String tittle;
    private String description;
    private String employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String managerComment;
}
