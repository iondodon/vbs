package com.babcock.vbs.dto;

import com.babcock.vbs.domain.entity.Vehicle;
import com.babcock.vbs.domain.type.FuelType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@Builder
public class VehicleDto {
    @NotNull
    private final UUID uuid;
    @NotNull
    @NotBlank
    private final String registrationNumber;
    @NotNull
    @NotBlank
    private final String make;
    @NotNull
    @NotBlank
    private final String model;
    @NotNull
    private final FuelType fuelType;
    @NotNull
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
