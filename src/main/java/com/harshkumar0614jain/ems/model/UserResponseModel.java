package com.harshkumar0614jain.ems.model;

import com.harshkumar0614jain.ems.enums.Role;
import com.harshkumar0614jain.ems.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserResponseModel {
    private String id;
    private String username;
    private String email;
    private Set<Role> roles;
    private UserStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant lastLoginDate;
}
