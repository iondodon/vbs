package com.babcock.vbs.controller.response;

import com.babcock.vbs.dto.VehicleDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public class AllVehiclesResponse {
    private final List<VehicleDto> vehicles;
}
