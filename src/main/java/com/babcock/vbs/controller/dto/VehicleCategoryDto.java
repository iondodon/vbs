package com.babcock.vbs.controller.dto;

import com.babcock.vbs.domain.entities.VehicleCategory;
import com.babcock.vbs.domain.entities.enumerations.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class VehicleCategoryDto {
    private final VehicleType category;
    private final BigDecimal pricePerDay;

    public static VehicleCategoryDto from(VehicleCategory category) {
        if (category == null) {
            return null;
        }
        return VehicleCategoryDto.builder()
                .category(category.getCategory())
                .pricePerDay(category.getPricePerDay())
                .build();
    }
}
