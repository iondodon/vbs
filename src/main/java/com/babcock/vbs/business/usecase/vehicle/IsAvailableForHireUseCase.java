package com.babcock.vbs.business.usecase.vehicle;

import com.babcock.vbs.business.UseCase;
import com.babcock.vbs.dto.DatePeriodDto;
import com.babcock.vbs.integration.database.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class IsAvailableForHireUseCase {
    private final VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public boolean forPeriod(UUID vehicleUuid, DatePeriodDto period) {
        return vehicleRepository
            .findBookedDatesForVehicleByPeriod(vehicleUuid, period.getFrom(), period.getTo())
            .isEmpty();
    }
}
