package main.java.opp_constructors.class_problems;
import java.util.Arrays;

public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Full constructor
    public FareSplitter(
            String tripId,
            double totalFare,
            int passengerCount) {

        if (tripId == null ||
            tripId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Trip ID cannot be blank."
            );
        }

        if (totalFare < 0) {

            throw new IllegalArgumentException(
                    "Fare cannot be negative."
            );
        }

        if (passengerCount <= 0) {

            throw new IllegalArgumentException(
                    "Passenger count must be positive."
            );
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }


    // Constructor with fare only
    public FareSplitter(
            String tripId,
            double totalFare) {

        this(tripId, totalFare, 2);
    }


    // Provisional constructor
    public FareSplitter(String tripId) {

        this(tripId, 0.0, 2);
    }


    public double[] fareBreakdown() {

        double[] result =
                new double[passengerCount];

        if (totalFare == 0) {
            return result;
        }

        double baseShare =
                Math.floor(
                        (totalFare / passengerCount)
                        * 100.0
                ) / 100.0;

        double assigned = 0;

        for (int i = 0; i < passengerCount - 1; i++) {

            result[i] = baseShare;
            assigned += baseShare;
        }

        // Last passenger receives the remainder.
        result[passengerCount - 1] =
                Math.round(
                        (totalFare - assigned) * 100.0
                ) / 100.0;

        return result;
    }


    public boolean isConfirmationOverdue(
            int confirmed,
            int expected) {

        if (confirmed < 0 || expected < 0) {
            return false;
        }

        return confirmed < expected;
    }


    public static void main(String[] args) {

        FareSplitter fare1 =
                new FareSplitter(
                        "TRIP001",
                        100000,
                        3
                );

        System.out.println(
                Arrays.toString(
                        fare1.fareBreakdown()
                )
        );


        FareSplitter fare2 =
                new FareSplitter("TRIP003");

        System.out.println(
                Arrays.toString(
                        fare2.fareBreakdown()
                )
        );


        System.out.println(
                "Confirmation overdue: "
                + fare1.isConfirmationOverdue(2, 3)
        );
    }
}