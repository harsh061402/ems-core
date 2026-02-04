package com.harshkumar0614jain.ems.entity;

import com.harshkumar0614jain.ems.enums.LeaveStatus;
import com.harshkumar0614jain.ems.enums.LeaveType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collation = "leave_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private String employeeId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private LeaveType leaveType;

    private LeaveStatus leaveStatus;
}
