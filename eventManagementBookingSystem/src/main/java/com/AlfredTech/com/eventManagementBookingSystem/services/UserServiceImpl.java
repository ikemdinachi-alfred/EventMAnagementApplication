package com.AlfredTech.com.eventManagementBookingSystem.services;

import com.AlfredTech.com.eventManagementBookingSystem.Security.user.Users;
import com.AlfredTech.com.eventManagementBookingSystem.data.models.User;
import com.AlfredTech.com.eventManagementBookingSystem.data.repositories.UserRepository;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.requests.LoginRequest;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.requests.RegistrationRequest;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.responses.LoginResponse;
import com.AlfredTech.com.eventManagementBookingSystem.dtos.responses.RegistrationResponse;
import com.AlfredTech.com.eventManagementBookingSystem.exceptions.InvalidDetailsException;
import com.AlfredTech.com.eventManagementBookingSystem.exceptions.InvalidPasswordException;
import com.AlfredTech.com.eventManagementBookingSystem.exceptions.NotAValidEmailException;
import com.AlfredTech.com.eventManagementBookingSystem.exceptions.UserExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Override
    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        passwordValidation(registrationRequest.getPassword());
        userEmailIsValid(registrationRequest.getEmail());
        Optional<User> foundUser = userExist(registrationRequest.getEmail());
        if (foundUser.isPresent()) { throw  new UserExistException
                (" user with email "+registrationRequest.getEmail()+" already exists"); }

        user.setEnabled(false);
        userRepository.save(user);
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setMessage("registration successful");
        return registrationResponse;
    }

    private void userEmailIsValid(String email) {
        if(!email.matches(EMAIL_REGEX)) throw
                new NotAValidEmailException("check "+ email + " is not a valid Email format ");
    }
    private void passwordValidation(String password) {
        if (password.length() <=7) {
            throw new InvalidPasswordException("password can not be less than 8 characters");
        }

    }
    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        Optional<User> foundEmail = userExist(loginRequest.getEmail());
        User foundUser = foundEmail.orElseThrow(() -> new InvalidDetailsException("invalid email"));
        if (!foundUser.getPassword().equals(loginRequest.getPassword())) {
            throw new InvalidDetailsException("invalid details");
        }
        foundUser.setEnabled(true);
        userRepository.save(foundUser);
        loginResponse.setMessage("login successful");
        return loginResponse;
    }

    @Override
    public Optional<User> userExist(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findUserByEmail(username)
              .orElseThrow(() -> new UsernameNotFoundException
                      (username + " not found"));
        return new Users(user);
    }
}
