package com.babcock.vbs.business.usecase.vehicle;

import com.babcock.vbs.business.UseCase;
import com.babcock.vbs.exception.ResourceNotFoundException;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Period;
import java.util.UUID;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GetCostOfHiringUseCase {
    private static final String VEHICLE_NOT_FOUND = "Vehicle not found";
    private final VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public BigDecimal byPeriod(UUID vehicleUuid, Period period) {
        log.info("Get price to hire vehicle {} for period {}", vehicleUuid, period);
        return vehicleRepository.findByUuid(vehicleUuid)
                .orElseThrow(() -> new ResourceNotFoundException(VEHICLE_NOT_FOUND))
                .getCategory()
                .getPricePerDay()
                .multiply(new BigDecimal(period.getDays()));
    }
}
