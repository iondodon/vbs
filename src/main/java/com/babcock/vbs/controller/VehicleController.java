package com.babcock.vbs.controller;

import com.babcock.vbs.business.validation.BookableDate;
import com.babcock.vbs.controller.response.AllVehiclesResponse;
import com.babcock.vbs.controller.response.AvailableForHireResponse;
import com.babcock.vbs.controller.response.CostResponse;
import com.babcock.vbs.presenter.VehiclePresenter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;


@Validated
@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehiclePresenter vehiclePresenter;

    @GetMapping
    @Operation(summary = "Get all the vehicles the company has")
    public AllVehiclesResponse getAllVehicles() {
        return vehiclePresenter.getAllVehicles();
    }

    @GetMapping(value = "/available")
    @Operation(summary = "Get available vehicles for hire on a given date")
    public AvailableForHireResponse getAvailableByDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @BookableDate LocalDate date
    ) {
        return vehiclePresenter.getAvailableForHireByDate(date);
    }

    @GetMapping("/{vehicleUuid}/cost")
    @Operation(summary = "Get cost of hiring a vehicle by date range")
    public CostResponse getCostByPeriod(
            @PathVariable("vehicleUuid") UUID vehicleUuid,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return vehiclePresenter.getCostByPeriod(vehicleUuid, from, to);
    }
}
