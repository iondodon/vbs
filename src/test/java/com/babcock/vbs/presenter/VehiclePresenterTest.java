package com.babcock.vbs.presenter;

import com.babcock.vbs.business.usecase.vehicle.GetAvailableForHireUseCase;
import com.babcock.vbs.business.usecase.vehicle.GetAllVehiclesUseCase;
import com.babcock.vbs.business.usecase.vehicle.GetCostOfHiringUseCase;
import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.controller.response.AvailableForHireResponse;
import com.babcock.vbs.domain.entity.Vehicle;
import com.babcock.vbs.exception.VbsException;
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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class VehiclePresenterTest {
    @Mock
    private GetAllVehiclesUseCase getAllVehiclesUseCase;
    @Mock
    private GetAvailableForHireUseCase getAvailableForHireUseCase;
    @Mock
    private GetCostOfHiringUseCase getCostOfHiringUseCase;
    @InjectMocks
    private VehiclePresenter vehiclePresenter;

    @Test
    void testReturnsCorrectVehicles() {
        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(randomUUID());
        List<Vehicle> vehicles = singletonList(vehicle);
        when(getAllVehiclesUseCase.exec()).thenReturn(vehicles);

        AllVehiclesResponse allVehicles = vehiclePresenter.getAllVehicles();

        assertThat(allVehicles.getVehicles()).hasSize(1);
        assertThat(allVehicles.getVehicles().get(0).getUuid())
                .isEqualTo(vehicle.getUuid());
    }

    @Test
    void testGetAvailableForHire_returnsCorrectVehicle() {
        LocalDate currentDate = LocalDate.now();
        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(UUID.randomUUID());

        when(getAvailableForHireUseCase.getByDate(any()))
                .thenReturn(singletonList(vehicle));

        AvailableForHireResponse availableVehicles = vehiclePresenter
                .getAvailableForHireByDate(currentDate);

        assertThat(availableVehicles.getVehicles()).hasSize(1);
        assertThat(availableVehicles.getVehicles().get(0).getUuid())
                .isEqualTo(vehicle.getUuid());
    }

    @Test
    void testGetCostOfHiringThrows_whenInvalidPeriod() {
        LocalDate from = LocalDate.now().plusDays(1);
        LocalDate to = LocalDate.now();

        assertThatExceptionOfType(VbsException.class)
                .isThrownBy(() -> vehiclePresenter.getCostByPeriod(randomUUID(), from, to))
                .withMessage("Invalid period");
    }

    @Test
    void testWasMultipliedToTheNumberOfDays() {
        UUID vehicleUuid = randomUUID();
        LocalDate from = LocalDate.now().minusDays(1);
        LocalDate to = LocalDate.now();

        vehiclePresenter.getCostByPeriod(vehicleUuid, from, to);

        verify(getCostOfHiringUseCase, times(1))
                .byPeriod(eq(vehicleUuid), any());
    }
}