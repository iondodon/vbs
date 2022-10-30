package com.babcock.vbs.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
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
        name = "bookings_bookingdates",
        joinColumns = @JoinColumn(name = "booking_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "bookingdate_id", nullable = false)
    )
    private Set<BookingDate> bookedDates = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

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
