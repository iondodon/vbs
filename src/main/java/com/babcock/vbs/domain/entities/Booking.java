package com.babcock.vbs.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@ToString
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "booking_id_generator")
    @SequenceGenerator(name = "booking_id_generator", sequenceName = "booking_id_generator")
    private Long id;

    @Type(type="uuid-char")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(nullable = false, unique = true, length = 36)
    private UUID uuid;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "bookings_reservation_dates",
        joinColumns = @JoinColumn(name = "booking_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "reservation_date_id", nullable = false)
    )
    private Set<ReservationDate> reservedDates = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() ||
                id == null || ((Booking) o).getId() == null) {
            return false;
        }
        Booking booking = (Booking) o;
        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
