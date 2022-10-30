package com.babcock.vbs.presenter;

import com.babcock.vbs.business.usecase.vehicle.GetAvailableForHireUseCase;
import com.babcock.vbs.business.usecase.vehicle.GetAllVehiclesUseCase;
import com.babcock.vbs.business.usecase.vehicle.GetCostOfHiringUseCase;
import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.controller.response.AvailableForHireResponse;
import com.babcock.vbs.controller.response.CostResponse;
import com.babcock.vbs.dto.VehicleDto;
import com.babcock.vbs.exception.VbsException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Presenter
@RequiredArgsConstructor
public class VehiclePresenter {
    private static final String INVALID_PERIOD = "Invalid period";
    private final GetAllVehiclesUseCase getAllVehiclesUseCase;
    private final GetAvailableForHireUseCase getAvailableForHireUseCase;
    private final GetCostOfHiringUseCase getCostOfHiringUseCase;

    public AllVehiclesResponse getAllVehicles() {
        List<VehicleDto> vehicleDtos = getAllVehiclesUseCase.exec()
                .stream()
                .map(VehicleDto::from)
                .collect(toList());
        return new AllVehiclesResponse(vehicleDtos);
    }

    public AvailableForHireResponse getAvailableForHireByDate(LocalDate date) {
        List<VehicleDto> availableVehicles = getAvailableForHireUseCase.getByDate(date)
                .stream()
                .map(VehicleDto::from)
                .collect(toList());
        return new AvailableForHireResponse(availableVehicles);
    }

    public CostResponse getCostByPeriod(UUID vehicleUuid, LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new VbsException(INVALID_PERIOD);
        }
        Period periodInclusive = Period.between(from, to.plusDays(1));
        BigDecimal cost = getCostOfHiringUseCase.byPeriod(vehicleUuid, periodInclusive);
        return new CostResponse(cost);
    }
}
