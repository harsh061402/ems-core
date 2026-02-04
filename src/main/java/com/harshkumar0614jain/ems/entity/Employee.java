package com.harshkumar0614jain.ems.entity;

import com.harshkumar0614jain.ems.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private Gender gender;

    @NotBlank
    private String email;

    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String designation;

    @NotBlank
    private Long salary;

    @NotBlank
    private String address;

    @NotBlank
    private User user;

}
