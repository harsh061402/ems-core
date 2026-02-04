package com.harshkumar0614jain.ems.modal;

import com.harshkumar0614jain.ems.entity.User;
import com.harshkumar0614jain.ems.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRequestModel {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Gender gender;

    @NotBlank
    private String email;

    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String designation;

    @NotBlank
    private Long Salary;

    @NotNull
    private String address;

    @NotNull
    private User user;
}
