package com.babcock.vbs.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@Entity
@Table(name = "bookingdate")
public class BookingDate {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "bookingdate_id_generator")
    @SequenceGenerator(
        name = "bookingdate_id_generator",
        sequenceName = "bookingdate_id_generator",
        initialValue = 100
    )
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private LocalDate bdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() ||
                id == null || ((BookingDate) o).getId() == null) {
            return false;
        }
        BookingDate bookingDate = (BookingDate) o;
        return id.equals(bookingDate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
