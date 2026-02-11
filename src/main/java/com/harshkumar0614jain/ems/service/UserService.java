package com.harshkumar0614jain.ems.service;

import com.harshkumar0614jain.ems.entity.User;
import com.harshkumar0614jain.ems.enums.Role;
import com.harshkumar0614jain.ems.enums.UserStatus;
import com.harshkumar0614jain.ems.exception.ResourceNotFoundException;
import com.harshkumar0614jain.ems.model.UserRequestModel;
import com.harshkumar0614jain.ems.model.UserResponseModel;
import com.harshkumar0614jain.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponseModel getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id " + id));
        return mapToResponse(user);
    }

    public UserResponseModel createUser(UserRequestModel userRequestModel) {
        User user = new User();
        user.setUserName(userRequestModel.getUserName());
        user.setPassword(userRequestModel.getPassword());
        user.setEmail(userRequestModel.getEmail());
        user.setUserRole(Role.EMPLOYEE);
        user.setUserStatus(UserStatus.ACTIVE);

        User response = userRepository.save(user);

        return mapToResponse(response);
    }

    private UserResponseModel mapToResponse(User user) {
        return new UserResponseModel(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getUserRole(),
                user.getUserStatus());
    }
}
