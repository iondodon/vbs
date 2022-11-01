package com.babcock.vbs.presenter;

import com.babcock.vbs.business.usecase.booking.BookVehicleUseCase;
import com.babcock.vbs.controller.request.CreateBookingRequest;
import lombok.RequiredArgsConstructor;

@Presenter
@RequiredArgsConstructor
public class BookingPresenter {
    private final BookVehicleUseCase bookVehicle;

    public void bookVehicle(CreateBookingRequest request) {
        bookVehicle.execForPeriod(request.getCustomerUuid(),
                request.getVehicleUuid(), request.getDatePeriod());
    }
}
