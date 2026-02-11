package com.harshkumar0614jain.ems.model;

import com.harshkumar0614jain.ems.entity.User;
import com.harshkumar0614jain.ems.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRequestModel {

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Mobile Number is mandatory")
    private String mobileNumber;

    @NotBlank(message = "Designation is mandatory")
    private String designation;

    @NotBlank(message = "Salary is mandatory")
    private Long salary;

    @NotNull(message = "Address is mandatory")
    private String address;

    @NotNull(message = "User is mandatory")
    private User user;
}
