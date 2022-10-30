package com.babcock.vbs.integration.database.repository;

import com.babcock.vbs.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
