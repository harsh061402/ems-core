package com.harshkumar0614jain.ems.model;

import com.harshkumar0614jain.ems.enums.Role;
import com.harshkumar0614jain.ems.enums.UserStatus;
import lombok.Data;

import java.util.Set;

@Data
public class UserUpdateRequestModel {

    private Set<Role> roles;
    private UserStatus status;
}
