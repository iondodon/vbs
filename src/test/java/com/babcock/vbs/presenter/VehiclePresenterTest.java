package com.babcock.vbs.presenter;

import com.babcock.vbs.business.usecase.vehicle.AvailableVehiclesForHire;
import com.babcock.vbs.business.usecase.vehicle.GetAllVehicles;
import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.controller.response.AvailableForHireResponse;
import com.babcock.vbs.domain.entities.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class VehiclePresenterTest {
    @Mock
    private GetAllVehicles getAllVehicles;
    @Mock
    private AvailableVehiclesForHire availableVehiclesForHire;
    @InjectMocks
    private VehiclePresenter vehiclePresenter;

    @Test
    void testReturnsCorrectVehicles() {
        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(randomUUID());
        List<Vehicle> vehicles = singletonList(vehicle);
        when(getAllVehicles.exec()).thenReturn(vehicles);

        AllVehiclesResponse allVehicles = vehiclePresenter.getAllVehicles();

        assertThat(allVehicles.getVehicles()).hasSize(1);
        assertThat(allVehicles.getVehicles().get(0).getUuid()).isEqualTo(vehicle.getUuid());
    }

    @Test
    void testGetAvailableForHire_returnsCorrectVehicle() {
        LocalDate currentDate = LocalDate.now();
        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(UUID.randomUUID());

        when(availableVehiclesForHire.getByDate(any())).thenReturn(singletonList(vehicle));

        AvailableForHireResponse availableVehicles = vehiclePresenter
                .getAvailableForHireByDate(currentDate);

        assertThat(availableVehicles.getVehicles()).hasSize(1);
        assertThat(availableVehicles.getVehicles().get(0).getUuid()).isEqualTo(vehicle.getUuid());
    }
}