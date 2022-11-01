package com.babcock.vbs.business.usecase.vehicle;

import com.babcock.vbs.domain.entity.Vehicle;
import com.babcock.vbs.domain.entity.VehicleCategory;
import com.babcock.vbs.domain.type.VehicleType;
import com.babcock.vbs.exception.ResourceNotFoundException;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Period;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCostOfHiringUseCaseTest {
    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private GetCostOfHiringUseCase getCostOfHiringUseCase;

    @Test
    void testThrowsResourceNotFoundException() {
        when(vehicleRepository.findByUuid(any())).thenReturn(Optional.empty());

        UUID vehicleUuid = randomUUID();
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> getCostOfHiringUseCase.byPeriod(vehicleUuid, Period.ofDays(1)))
                .withMessage(format("Vehicle with UUID %s not found", vehicleUuid));
    }

    @Test
    void testCalculatesCorrectCost() {
        VehicleCategory vehicleCategory = new VehicleCategory();
        vehicleCategory.setCategory(VehicleType.VAN);
        vehicleCategory.setPricePerDay(BigDecimal.TEN);

        Vehicle vehicle = new Vehicle();
        vehicle.setUuid(randomUUID());
        vehicle.setCategory(vehicleCategory);

        when(vehicleRepository.findByUuid(any()))
                .thenReturn(Optional.of(vehicle));

        BigDecimal cost = getCostOfHiringUseCase
                .byPeriod(vehicle.getUuid(), Period.ofDays(2));

        assertThat(cost).isEqualTo(BigDecimal.valueOf(20));
    }
}