package com.harshkumar0614jain.ems.modal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestModel {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @Email
    private String email;
}
