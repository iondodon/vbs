package com.babcock.vbs.dto;

import com.babcock.vbs.domain.entity.VehicleCategory;
import com.babcock.vbs.domain.type.VehicleType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class VehicleCategoryDto {
    @NotNull
    private final VehicleType category;
    @NotNull
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
