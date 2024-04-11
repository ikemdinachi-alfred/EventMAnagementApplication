package com.AlfredTech.com.eventManagementBookingSystem.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100)
    private String eventName;
    @Size(min = 1, max = 500)
    @Column(length = 500)
    @NotNull
    private String description;
    private String location;
    private LocalDate eventDate;
    @Column(length = 1000)
    @Size(min = 1, max = 1000)
    private Integer attendees ;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "event_category")
    private Category categories;


}
