package com.harshkumar0614jain.ems.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private String key;
    private String message;
}
