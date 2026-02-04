package com.harshkumar0614jain.ems.modal;

import com.harshkumar0614jain.ems.enums.LeaveStatus;
import com.harshkumar0614jain.ems.enums.LeaveType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestModel {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private String employeeId;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @Future
    private LocalDate endDate;

    @NotNull
    private LeaveType leaveType;
}
