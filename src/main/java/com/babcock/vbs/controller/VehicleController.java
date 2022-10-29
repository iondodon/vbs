package com.babcock.vbs.controller;

import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.controller.response.AvailableForHireResponse;
import com.babcock.vbs.presenter.VehiclePresenter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
public class VehicleController {
    private final VehiclePresenter vehiclePresenter;

    @GetMapping("/api/v1/vehicles")
    @Operation(summary = "Get all the vehicles the company has.")
    public AllVehiclesResponse getAllVehicles() {
        return vehiclePresenter.getAllVehicles();
    }

    @GetMapping("/api/v1/vehicles/available")
    @Operation(summary = "Get available vehicles for hire on a given date.")
    public AvailableForHireResponse getAvailableByDate(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @RequestParam LocalDate date
    ) {
        return vehiclePresenter.getAvailableForHireByDate(date);
    }
}
