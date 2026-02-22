package com.harshkumar0614jain.ems.entity;

import com.harshkumar0614jain.ems.enums.LeaveStatus;
import com.harshkumar0614jain.ems.enums.LeaveType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "leave_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveRequest {
    @Id
    private String id;

    private String title;

    private String description;

    private String employeeId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LeaveType leaveType;

    private LeaveStatus leaveStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String managerComment;
}
