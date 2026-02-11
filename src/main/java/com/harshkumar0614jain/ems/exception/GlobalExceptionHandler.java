package com.harshkumar0614jain.ems.exception;

import com.harshkumar0614jain.ems.model.ExceptionResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseModel> handleException(Exception e) {
        ExceptionResponseModel error  = new ExceptionResponseModel(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        ExceptionResponseModel error = new ExceptionResponseModel("Validation is failed ",errors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseModel> handleIllegalArgumentException(IllegalArgumentException e) {
        ExceptionResponseModel error = new ExceptionResponseModel(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseModel> handleResourceNotFoundException(ResourceNotFoundException e) {
        ExceptionResponseModel error = new ExceptionResponseModel(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
