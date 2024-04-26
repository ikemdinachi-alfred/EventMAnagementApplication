package com.AlfredTech.com.eventManagementBookingSystem.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AuthLoginRequest {
    private String email;
    private String password;
}
