package main.java.abstract_design_patterns.class_problems;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public abstract double calculatePrice(long nights);

    public String getRoomNumber() {
        return roomNumber;
    }
}

class StandardRoom extends Room {

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 150;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 200;
    }
}

class SuiteRoom extends Room {

    public SuiteRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 300;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private Room room;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    public Reservation(
            Room room,
            Customer customer,
            LocalDate startDate,
            LocalDate endDate) {

        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
    }

    public boolean overlaps(
            LocalDate start,
            LocalDate end) {

        return active
                && start.isBefore(endDate)
                && end.isAfter(startDate);
    }

    public void cancel() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public Room getRoom() {
        return room;
    }

    public double getPrice() {
        long nights =
                java.time.temporal.ChronoUnit.DAYS.between(
                        startDate,
                        endDate
                );

        return room.calculatePrice(nights);
    }
}

class BookingManager {

    private List<Reservation> reservations =
            new ArrayList<>();

    public Reservation book(
            Room room,
            Customer customer,
            LocalDate start,
            LocalDate end) {

        for (Reservation reservation : reservations) {

            if (reservation.getRoom() == room
                    && reservation.overlaps(start, end)) {

                System.out.println(
                        "Booking failed: Room "
                                + room.getRoomNumber()
                                + " is not available."
                );

                return null;
            }
        }

        Reservation reservation =
                new Reservation(
                        room,
                        customer,
                        start,
                        end
                );

        reservations.add(reservation);

        System.out.printf(
                "%s booked from %s to %s. Total price: $%.2f%n",
                room.getRoomNumber(),
                start,
                end,
                reservation.getPrice()
        );

        return reservation;
    }

    public void cancel(Reservation reservation) {

        if (reservation != null
                && reservation.isActive()) {

            reservation.cancel();

            System.out.println(
                    "Reservation for Room "
                            + reservation.getRoom().getRoomNumber()
                            + " cancelled successfully."
            );
        }
    }
}

public class HotelBookingSystem {

    public static void main(String[] args) {

        BookingManager manager = new BookingManager();

        Customer customer =
                new Customer("Swetha");

        Room deluxe =
                new DeluxeRoom("Deluxe Room 101");

        Room standard =
                new StandardRoom("Standard Room 205");

        Reservation r1 = manager.book(
                deluxe,
                customer,
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5)
        );

        Reservation r2 = manager.book(
                standard,
                customer,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        manager.book(
                deluxe,
                customer,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        manager.cancel(r1);
    }
}