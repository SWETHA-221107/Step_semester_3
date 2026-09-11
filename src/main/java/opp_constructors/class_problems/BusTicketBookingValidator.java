package main.java.opp_constructors.class_problems;
import java.util.HashSet;

class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    // Only parameterized constructor is provided.
    // This prevents creation of an empty BusTicket.
    public BusTicket(String passengerName, String destination) {

        if (passengerName == null ||
            passengerName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Passenger name cannot be blank or null."
            );
        }

        // A name should contain only letters and spaces.
        for (int i = 0; i < passengerName.length(); i++) {

            char ch = passengerName.charAt(i);

            if (!Character.isLetter(ch) && ch != ' ') {

                throw new IllegalArgumentException(
                        "Passenger name must contain only letters."
                );
            }
        }

        if (destination == null ||
            destination.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Destination cannot be blank or null."
            );
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    public void markCheckedIn() {

        if (!checkedIn) {
            checkedIn = true;
            System.out.println(
                    passengerName + " checked in successfully."
            );
        } else {
            System.out.println(
                    passengerName + " is already checked in."
            );
        }
    }

    public String getBookingKey() {
        return passengerName.toLowerCase()
                + "|"
                + destination.toLowerCase();
    }

    public static void processBatch(String[][] rawBookings) {

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        HashSet<String> acceptedBookings = new HashSet<>();

        for (String[] booking : rawBookings) {

            try {

                if (booking == null || booking.length < 2) {
                    rejected++;
                    continue;
                }

                String passengerName = booking[0];
                String destination = booking[1];

                BusTicket ticket =
                        new BusTicket(
                                passengerName,
                                destination
                        );

                String key = ticket.getBookingKey();

                if (acceptedBookings.contains(key)) {

                    duplicates++;

                } else {

                    acceptedBookings.add(key);
                    valid++;
                }

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        System.out.println(
                "Valid: " + valid
                + " | Rejected: " + rejected
                + " | Duplicates skipped: "
                + duplicates
        );
    }
}


public class BusTicketBookingValidator {

    public static void main(String[] args) {

        String[][] rawBookings = {

            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        BusTicket.processBatch(rawBookings);


        System.out.println("\nCheck-in test:");

        BusTicket ticket =
                new BusTicket(
                        "Divya",
                        "Chennai"
                );

        ticket.markCheckedIn();
        ticket.markCheckedIn();
    }
}
