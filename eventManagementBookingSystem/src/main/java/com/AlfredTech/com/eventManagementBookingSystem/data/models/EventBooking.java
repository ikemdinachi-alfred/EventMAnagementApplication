package com.AlfredTech.com.eventManagementBookingSystem.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_event_booking_details")
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email_address")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(min = 1, max = 1000)
    private Long reservationId;

}
