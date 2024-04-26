package com.AlfredTech.com.eventManagementBookingSystem.controllers;

import com.AlfredTech.com.eventManagementBookingSystem.dtos.requests.LoginRequest;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.requests.RegistrationRequest;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.responses.LoginResponse;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.responses.RegistrationResponse;
import com.AlfredTech.com.eventManagementBookingSystem.exceptions.InvalidDetailsException;
import com.AlfredTech.com.eventManagementBookingSystem.exceptions.InvalidPasswordException;
import com.AlfredTech.com.eventManagementBookingSystem.exceptions.NotAValidEmailException;
import com.AlfredTech.com.eventManagementBookingSystem.exceptions.UserExistException;
import com.AlfredTech.com.eventManagementBookingSystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public RegistrationResponse registerUser(@RequestBody RegistrationRequest registrationRequest) {
        RegistrationResponse registrationResponse = new RegistrationResponse();
        try {
            return userService.registerUser(registrationRequest);
        } catch (UserExistException | NotAValidEmailException | InvalidPasswordException exception) {
            registrationResponse.setMessage(exception.getMessage());
            return registrationResponse;
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("login successful");
        try {
            return userService.loginUser(loginRequest);
        } catch (InvalidDetailsException exception) {
            loginResponse.setMessage(exception.getMessage());
            return loginResponse;
        }

    }
}
