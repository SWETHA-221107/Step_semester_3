package main.java.opp_constructors.class_problems;

public final class TieredBoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;


    // Constructor
    public TieredBoardingPenaltyCalculator(
            double minimumPenaltyPercent) {

        if (minimumPenaltyPercent < 0) {

            throw new IllegalArgumentException(
                    "Minimum penalty percentage cannot be negative."
            );
        }

        this.minimumPenaltyPercent =
                minimumPenaltyPercent;
    }


    // final method cannot be overridden
    public final double calculatePenalty(
            double ticketFare,
            int minutesLate) {

        if (ticketFare < 0) {

            throw new IllegalArgumentException(
                    "Ticket fare cannot be negative."
            );
        }

        if (minutesLate < 0) {

            throw new IllegalArgumentException(
                    "Minutes late cannot be negative."
            );
        }


        // On-time boarding has no penalty.
        if (minutesLate == 0) {
            return 0.0;
        }


        double penalty = 0.0;


        // Minutes 1-5 = 0.5% per minute
        int firstTier =
                Math.min(minutesLate, 5);

        penalty +=
                firstTier
                * ticketFare
                * 0.005;


        // Minutes 6-15 = 1% per minute
        if (minutesLate > 5) {

            int secondTier =
                    Math.min(minutesLate, 15) - 5;

            penalty +=
                    secondTier
                    * ticketFare
                    * 0.01;
        }


        // Minutes 16 onward = 2% per minute
        if (minutesLate > 15) {

            int thirdTier =
                    minutesLate - 15;

            penalty +=
                    thirdTier
                    * ticketFare
                    * 0.02;
        }


        // Flat-fee floor
        double minimumPenalty =
                ticketFare
                * minimumPenaltyPercent
                / 100.0;


        // Charge the larger amount.
        penalty =
                Math.max(
                        penalty,
                        minimumPenalty
                );


        return Math.round(
                penalty * 100.0
        ) / 100.0;
    }


    public static void main(String[] args) {

        TieredBoardingPenaltyCalculator calculator =
                new TieredBoardingPenaltyCalculator(1.0);


        System.out.println(
                "0 minutes: Rs "
                + calculator.calculatePenalty(
                        1000,
                        0
                )
        );


        System.out.println(
                "1 minute: Rs "
                + calculator.calculatePenalty(
                        1000,
                        1
                )
        );


        System.out.println(
                "16 minutes: Rs "
                + calculator.calculatePenalty(
                        1000,
                        16
                )
        );
    }
}