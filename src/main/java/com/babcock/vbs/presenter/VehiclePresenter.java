package com.babcock.vbs.presenter;

import com.babcock.vbs.business.usecase.vehicle.AvailableVehiclesForHire;
import com.babcock.vbs.business.usecase.vehicle.GetAllVehicles;
import com.babcock.vbs.controller.dto.VehicleDto;
import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.controller.response.AvailableForHireResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Presenter
@RequiredArgsConstructor
public class VehiclePresenter {
    private final GetAllVehicles getAllVehicles;
    private final AvailableVehiclesForHire availableVehiclesForHire;

    public AllVehiclesResponse getAllVehicles() {
        List<VehicleDto> vehicleDtos = getAllVehicles.exec()
                .stream()
                .map(VehicleDto::from)
                .toList();
        return new AllVehiclesResponse(vehicleDtos);
    }

    public AvailableForHireResponse getAvailableForHireByDate(LocalDate date) {
        List<VehicleDto> availableVehicles = availableVehiclesForHire.getByDate(date)
                .stream()
                .map(VehicleDto::from)
                .toList();
        return new AvailableForHireResponse(availableVehicles);
    }
}
