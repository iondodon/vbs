package com.babcock.vbs.controller;

import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.presenter.VehiclePresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class VehicleController {
    private final VehiclePresenter vehiclePresenter;

    @GetMapping("/api/v1/vehicles")
    public AllVehiclesResponse getAllVehicles() {
        return vehiclePresenter.getAllVehicles();
    }
}
