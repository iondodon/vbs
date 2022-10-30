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

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCostOfHiringTest {
    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private GetCostOfHiring getCostOfHiring;

    @Test
    void testThrowsResourceNotFoundException() {
        when(vehicleRepository.findByUuid(any())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> getCostOfHiring.byPeriod(randomUUID(), Period.ofDays(1)))
                .withMessage("Vehicle not found");
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

        BigDecimal cost = getCostOfHiring
                .byPeriod(vehicle.getUuid(), Period.ofDays(2));

        assertThat(cost).isEqualTo(BigDecimal.valueOf(20));
    }
}