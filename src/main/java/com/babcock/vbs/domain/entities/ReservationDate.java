package com.babcock.vbs.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@ToString
@Entity
@Table(name = "reservation_date")
public class ReservationDate {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "reservation_date_id_generator")
    @SequenceGenerator(
            name = "reservation_date_id_generator",
            sequenceName = "reservation_date_id_generator"
    )
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() ||
                id == null || ((ReservationDate) o).getId() == null) {
            return false;
        }
        ReservationDate reservationDate = (ReservationDate) o;
        return id.equals(reservationDate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
