package com.babcock.vbs.business.usecase.vehicle;


import com.babcock.vbs.business.UseCase;
import com.babcock.vbs.domain.entities.Vehicle;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class AvailableVehiclesForHire {
    private final VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public List<Vehicle> getByDate(LocalDate date) {
        return vehicleRepository.getAvailableForHireOnDate(date);
    }
}
