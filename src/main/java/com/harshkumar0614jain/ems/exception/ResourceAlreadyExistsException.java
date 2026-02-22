package com.harshkumar0614jain.ems.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ResourceAlreadyExistsException extends RuntimeException{
    private String key;
    private String message;

}
