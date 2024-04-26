package com.AlfredTech.com.eventManagementBookingSystem.services;

import com.AlfredTech.com.eventManagementBookingSystem.data.models.User;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.requests.LoginRequest;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.requests.RegistrationRequest;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.responses.LoginResponse;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.responses.RegistrationResponse;

import java.util.Optional;

public interface UserService {
    RegistrationResponse registerUser(RegistrationRequest registrationRequest);
    LoginResponse loginUser(LoginRequest loginRequest);
    Optional<User> userExist(String email);
}
