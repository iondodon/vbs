package com.babcock.vbs.business.usecase.vehicle;


import com.babcock.vbs.business.UseCase;
import com.babcock.vbs.domain.entity.Vehicle;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class AvailableVehiclesForHire {
    private final VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public List<Vehicle> getByDate(LocalDate date) {
        log.info("Get all available vehicles on date {}", date);
        return vehicleRepository.getAvailableForHireOnDate(date);
    }
}
