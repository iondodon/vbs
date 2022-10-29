package com.babcock.vbs.business.usecase.vehicle;

import com.babcock.vbs.business.UseCase;
import com.babcock.vbs.domain.entities.Vehicle;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GetAllVehicles {
    private final VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public List<Vehicle> exec() {
        log.info("Get all vehicles");
        return vehicleRepository.findAll();
    }
}
