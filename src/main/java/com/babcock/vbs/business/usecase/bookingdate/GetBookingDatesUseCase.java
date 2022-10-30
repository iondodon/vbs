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
    public Set<BookingDate> forPeriod(LocalDate from, LocalDate to) {
        log.info("Get booking dates for period {} - {}", from, to);
        Set<BookingDate> persistedBookingDates = bookingDateRepository
                .findAllInPeriodInclusive(from, to);

        Set<LocalDate> persistedDates = persistedBookingDates.stream()
                .map(BookingDate::getBdate)
                .collect(Collectors.toSet());

        LocalDate date = from;
        while (date.isBefore(to) || date.isEqual(to)) {
            if (!persistedDates.contains(date)) {
                persistNewDate(persistedBookingDates, date);
            }
            date = date.plusDays(1);
        }

        return persistedBookingDates;
    }

    private void persistNewDate(Set<BookingDate> persistedBookingDates, LocalDate date) {
        BookingDate newBookingDate = new BookingDate();
        newBookingDate.setBdate(date);
        BookingDate persistedBookingDate = bookingDateRepository.save(newBookingDate);
        persistedBookingDates.add(persistedBookingDate);
    }
}
