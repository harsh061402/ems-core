package com.harshkumar0614jain.ems.controller;

import com.harshkumar0614jain.ems.modal.ResponseModel;
import com.harshkumar0614jain.ems.modal.UserRequestModel;
import com.harshkumar0614jain.ems.modal.UserResponseModel;
import com.harshkumar0614jain.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseModel<List<UserResponseModel>>> getAllUser(){
        ResponseModel<List<UserResponseModel>> response = new ResponseModel<>("List of users",
                userService.getAllUsers());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseModel<UserResponseModel>> getUser(@PathVariable String userId){
        ResponseModel<UserResponseModel>  response = new ResponseModel<>("User is found",
                userService.getUserById(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel<UserResponseModel>> createUser(@RequestBody UserRequestModel userRequest){
        ResponseModel<UserResponseModel>  response = new ResponseModel<>("User is created successfully",
                userService.createUser(userRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
