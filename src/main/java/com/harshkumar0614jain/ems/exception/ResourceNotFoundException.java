package com.harshkumar0614jain.ems.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private String message;
    private String data;

    public ResourceNotFoundException(String s) {
        this.message = s;
    }
}
