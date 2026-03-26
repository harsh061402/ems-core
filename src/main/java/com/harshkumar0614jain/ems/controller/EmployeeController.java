package com.harshkumar0614jain.ems.controller;

import com.harshkumar0614jain.ems.model.EmployeeRequestModel;
import com.harshkumar0614jain.ems.model.EmployeeResponseModel;
import com.harshkumar0614jain.ems.model.EmployeeUpdateRequestModel;
import com.harshkumar0614jain.ems.model.ResponseModel;
import com.harshkumar0614jain.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseModel> createEmployee(
            @Valid @RequestBody EmployeeRequestModel employeeRequest){

        EmployeeResponseModel employeeCreated = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseModel<List<EmployeeResponseModel>>> getAllEmployees(){

        List<EmployeeResponseModel> userList = employeeService.findAllEmployee();
        ResponseModel<List<EmployeeResponseModel>> response = new ResponseModel<>(
                "List of Employees retrieved successfully ",userList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<EmployeeResponseModel>> getEmployeeById(@PathVariable String id){
        EmployeeResponseModel employee = employeeService.findByEmployeeId(id);
        ResponseModel<EmployeeResponseModel> response = new ResponseModel<>(
                "Employee retrieved successfully ",employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseModel<EmployeeResponseModel>> updateEmployee(
            @PathVariable String id,
            @Valid @RequestBody EmployeeUpdateRequestModel employeeUpdateRequest){

        EmployeeResponseModel updatedEmployee = employeeService.updateEmployee(id,employeeUpdateRequest);
        ResponseModel<EmployeeResponseModel> response = new ResponseModel<>(
                "Employee updated successfully ",updatedEmployee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseModel<Void>> deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
        ResponseModel<Void> response = new ResponseModel<>("Employee deleted successfully ",null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
