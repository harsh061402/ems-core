package com.harshkumar0614jain.ems.service;

import com.harshkumar0614jain.ems.entity.Address;
import com.harshkumar0614jain.ems.entity.Employee;
import com.harshkumar0614jain.ems.exception.ResourceAlreadyExistsException;
import com.harshkumar0614jain.ems.exception.ResourceNotFoundException;
import com.harshkumar0614jain.ems.model.AddressModel;
import com.harshkumar0614jain.ems.model.EmployeeRequestModel;
import com.harshkumar0614jain.ems.model.EmployeeResponseModel;
import com.harshkumar0614jain.ems.repository.EmployeeRepository;
import com.harshkumar0614jain.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private UserRepository userRepo;

    public EmployeeResponseModel mapToEmployeeResponse(Employee employee){
        return EmployeeResponseModel.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .mobileNumber(employee.getMobileNumber())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .currentAddress(
                        mapToAddressModel(employee.getCurrentAddress()))
                .permanentAddress(
                        mapToAddressModel(employee.getPermanentAddress()))
                .userId(employee.getUserId())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

    public Address mapToAddress(AddressModel addressModel){
        return Address.builder()
                .street(addressModel.getStreet())
                .city(addressModel.getCity())
                .state(addressModel.getState())
                .zipCode(addressModel.getZipCode())
                .country(addressModel.getCountry())
                .build();
    }

    public AddressModel mapToAddressModel(Address address){
        return AddressModel.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .build();
    }

    public EmployeeResponseModel createEmployee(EmployeeRequestModel employeeRequest) {

        if(!userRepo.existsById(employeeRequest.getUserId()))
            throw new ResourceNotFoundException("userId","Invalid User Id");

        if(employeeRepo.existsByUserId(employeeRequest.getUserId()))
            throw new ResourceAlreadyExistsException("userId","Employee already exists");

        if(employeeRepo.existsByMobileNumber(employeeRequest.getMobileNumber()))
            throw new ResourceAlreadyExistsException("mobileNumber"," Mobile Number already exists");

        String normalised = employeeRequest.getMobileNumber().replaceAll(" ","");

        Employee employee = Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .gender(employeeRequest.getGender())
                .mobileNumber(employeeRequest.getMobileNumber())
                .department(employeeRequest.getDepartment())
                .designation(employeeRequest.getDesignation())
                .salary(employeeRequest.getSalary())
                .currentAddress(
                        mapToAddress(employeeRequest.getCurrentAddress()))
                .permanentAddress(
                        mapToAddress(employeeRequest.getPermanentAddress()))
                .userId(employeeRequest.getUserId())
                .build();

        Employee createdEmp = employeeRepo.save(employee);

        return mapToEmployeeResponse(createdEmp);
    }


    public List<EmployeeResponseModel> findAllEmployee() {
        return employeeRepo.findAll()
                .stream()
                .map(this::mapToEmployeeResponse)
                .toList();
    }

    public EmployeeResponseModel findByEmployeeId(String id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("employeeId",
                        "Employee not found of this Id:-"+ id));
        return mapToEmployeeResponse(employee);
    }
}
