package com.harshkumar0614jain.ems.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceAlreadyExistsException extends RuntimeException{
    private String key;
    private String message;

}
