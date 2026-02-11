package com.harshkumar0614jain.ems.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel<T> {
    private String message;
    private T data;
}
