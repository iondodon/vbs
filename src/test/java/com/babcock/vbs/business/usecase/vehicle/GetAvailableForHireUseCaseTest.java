package com.babcock.vbs.business.usecase.vehicle;

import com.babcock.vbs.domain.entity.Vehicle;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAvailableForHireUseCaseTest {
    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private GetAvailableForHireUseCase getAvailableForHireUseCase;

    @Test
    void testGetByDateReturnCorrectVehicles() {
        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(UUID.randomUUID());
        LocalDate currentDate = LocalDate.now();

        when(vehicleRepository.getAvailableForHireOnDate(currentDate))
                .thenReturn(singletonList(vehicle));

        List<Vehicle> availableVehicles = getAvailableForHireUseCase.getByDate(currentDate);

        assertThat(availableVehicles).hasSize(1);
        assertThat(availableVehicles.get(0).getUuid()).isEqualTo(vehicle.getUuid());
    }

    @Test
    void testCorrectAvailableVehicleReturned() {
        LocalDate date = LocalDate.of(2022, 6, 14);
        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(UUID.randomUUID());

        when(vehicleRepository.getAvailableForHireOnDate(any()))
                .thenReturn(singletonList(vehicle));

        List<Vehicle> availableVehicles = getAvailableForHireUseCase.getByDate(date);
        assertThat(availableVehicles).hasSize(1);
        assertThat(availableVehicles.get(0).getUuid()).isEqualTo(vehicle.getUuid());
    }
}