package com.babcock.vbs.business.usecase;

import com.babcock.vbs.business.UseCase;
import com.babcock.vbs.domain.entities.Vehicle;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class GetAllVehicles {
    private final VehicleRepository vehicleRepository;

    public List<Vehicle> exec() {
        return vehicleRepository.findAll();
    }
}
