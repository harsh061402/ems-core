package com.harshkumar0614jain.ems.entity;

import com.harshkumar0614jain.ems.enums.Gender;
import com.harshkumar0614jain.ems.enums.Role;
import com.harshkumar0614jain.ems.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotNull
    private Role userRole;

    @NotNull
    private UserStatus userStatus;

    @CreatedDate
    private LocalDateTime createdDate;
}
