package com.babcock.vbs.dto;

import com.babcock.vbs.domain.entity.Vehicle;
import com.babcock.vbs.domain.type.FuelType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class VehicleDto {
    private final UUID uuid;
    private final String registrationNumber;
    private final String make;
    private final String model;
    private final FuelType fuelType;
    private final VehicleCategoryDto vehicleCategory;


    public static VehicleDto from(Vehicle vehicle) {
        return VehicleDto.builder()
            .uuid(vehicle.getUuid())
            .registrationNumber(vehicle.getRegistrationNumber())
            .make(vehicle.getMake())
            .model(vehicle.getModel())
            .fuelType(vehicle.getFuelType())
            .vehicleCategory(VehicleCategoryDto.from(vehicle.getCategory()))
            .build();
    }
}
