package com.babcock.vbs.business.usecase.bookingdate;

import com.babcock.vbs.business.UseCase;
import com.babcock.vbs.domain.entity.BookingDate;
import com.babcock.vbs.integration.database.repository.BookingDateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GetBookingDatesUseCase {
    private final BookingDateRepository bookingDateRepository;


    @Transactional
    public Set<BookingDate> getForPeriod(LocalDate fromDate, LocalDate toDate) {
        log.info("Get booking dates for period {} - {}", fromDate, toDate);
        Set<BookingDate> persistedBookingDates = bookingDateRepository
                .findAllInPeriodInclusive(fromDate, toDate);

        Set<LocalDate> persistedDates = persistedBookingDates.stream()
                .map(BookingDate::getBdate)
                .collect(Collectors.toSet());

        LocalDate date = fromDate;
        while (date.isBefore(toDate) || date.isEqual(toDate)) {
            if (!persistedDates.contains(date)) {
                persistNewDate(persistedBookingDates, date);
            }
            date = date.plusDays(1);
        }

        return persistedBookingDates;
    }

    private void persistNewDate(Set<BookingDate> persistedBookingDates, LocalDate newDate) {
        BookingDate newBookingDate = new BookingDate();
        newBookingDate.setBdate(newDate);
        BookingDate persistedBookingDate = bookingDateRepository.save(newBookingDate);
        persistedBookingDates.add(persistedBookingDate);
    }
}
