package com.babcock.vbs.controller.response;

import com.babcock.vbs.controller.dto.VehicleDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
public class AvailableForHireResponse {
    private final List<VehicleDto> vehicles;
}
