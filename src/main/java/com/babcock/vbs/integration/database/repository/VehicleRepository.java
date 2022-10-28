package com.babcock.vbs.integration.database.repository;

import com.babcock.vbs.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
