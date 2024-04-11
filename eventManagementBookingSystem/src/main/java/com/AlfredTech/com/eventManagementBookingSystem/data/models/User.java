package com.AlfredTech.com.eventManagementBookingSystem.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Table(name = "users_details")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Size(min = 4)
    @NotNull
    private String password;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> events;
}
