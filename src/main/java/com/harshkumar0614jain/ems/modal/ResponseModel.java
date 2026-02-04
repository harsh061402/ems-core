package com.harshkumar0614jain.ems.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseModel<T> {
    private String message;
    private T data;
}
