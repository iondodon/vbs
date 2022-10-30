package com.babcock.vbs.business.usecase;

import com.babcock.vbs.business.usecase.vehicle.GetAllVehicles;
import com.babcock.vbs.domain.entity.Vehicle;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetAllVehiclesTest {
    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private GetAllVehicles getAllVehicles;

    @Test
    void testReturnsCorrectVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(randomUUID());
        when(vehicleRepository.findAll()).thenReturn(singletonList(vehicle));

        List<Vehicle> allVehicles = getAllVehicles.exec();

        assertThat(allVehicles).hasSize(1);
        assertThat(allVehicles.get(0).getUuid()).isEqualTo(vehicle.getUuid());
    }
}