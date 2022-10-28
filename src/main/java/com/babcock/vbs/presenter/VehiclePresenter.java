package com.babcock.vbs.presenter;

import com.babcock.vbs.business.usecase.GetAllVehicles;
import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.controller.dto.VehicleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VehiclePresenter {
    private final GetAllVehicles getAllVehicles;

    public AllVehiclesResponse getAllVehicles() {
        List<VehicleDto> vehicleDtos = getAllVehicles.exec()
                .stream()
                .map(VehicleDto::from)
                .toList();
        return new AllVehiclesResponse(vehicleDtos);
    }
}
