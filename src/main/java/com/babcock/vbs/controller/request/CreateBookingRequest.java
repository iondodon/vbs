package com.babcock.vbs.controller.request;


import com.babcock.vbs.business.validation.ValidPeriod;
import com.babcock.vbs.dto.DatePeriodDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Getter
public class CreateBookingRequest {
    @NotNull
    private final UUID customerUuid;

    @NotNull
    private final UUID vehicleUuid;

    @NotNull
    @ValidPeriod
    private final DatePeriodDto datePeriod;
}
