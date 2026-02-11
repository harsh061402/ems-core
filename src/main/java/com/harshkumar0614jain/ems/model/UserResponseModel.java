package com.harshkumar0614jain.ems.model;

import com.harshkumar0614jain.ems.enums.Role;
import com.harshkumar0614jain.ems.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponseModel {
    private String id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private UserStatus userStatus;
    private LocalDateTime createdDate;
}
