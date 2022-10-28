package com.babcock.vbs.presenter;

import com.babcock.vbs.business.usecase.GetAllVehicles;
import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.domain.entities.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class VehiclePresenterTest {
    @Mock
    private GetAllVehicles getAllVehicles;
    @InjectMocks
    private VehiclePresenter vehiclePresenter;

    @Test
    void testReturnsCorrectVehicles() {
        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(randomUUID());
        List<Vehicle> vehicles = List.of(vehicle);
        when(getAllVehicles.exec()).thenReturn(vehicles);

        AllVehiclesResponse allVehicles = vehiclePresenter.getAllVehicles();

        assertThat(allVehicles.getVehicles()).hasSize(1);
        assertThat(allVehicles.getVehicles().get(0).getUuid()).isEqualTo(vehicle.getUuid());
    }
}