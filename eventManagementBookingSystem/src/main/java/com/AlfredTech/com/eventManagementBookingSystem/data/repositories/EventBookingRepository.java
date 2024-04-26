package com.AlfredTech.com.eventManagementBookingSystem.data.repositories;

import com.AlfredTech.com.eventManagementBookingSystem.data.models.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {
}
