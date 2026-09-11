package main.java.opp_constructors.assignment_problems;
public final class ExamWeekSurgeFeeCalculator {

    // Configured minimum surge percentage
    private final double minimumSurgePercent;


    // Constructor
    public ExamWeekSurgeFeeCalculator(
            double minimumSurgePercent) {

        if (minimumSurgePercent < 0) {

            throw new IllegalArgumentException(
                    "Minimum surge percent cannot be negative."
            );
        }

        this.minimumSurgePercent =
                minimumSurgePercent;
    }


    // Calculation rule cannot be overridden
    public final double calculateSurgeFee(
            double orderValue,
            int delayMinutes) {

        // Validation happens at calculation time.
        if (orderValue < 0) {

            throw new IllegalArgumentException(
                    "Order value cannot be negative."
            );
        }

        if (delayMinutes < 0) {

            throw new IllegalArgumentException(
                    "Delay minutes cannot be negative."
            );
        }


        // On-time order has no surge fee.
        // The minimum floor must NOT apply.
        if (delayMinutes == 0) {

            return 0.0;
        }


        double surgeFee = 0.0;


        // ------------------------------------------
        // Tier 1: Minutes 1-5
        // 0.5% per minute
        // ------------------------------------------

        int firstTierMinutes =
                Math.min(delayMinutes, 5);

        surgeFee +=
                firstTierMinutes
                * orderValue
                * 0.005;


        // ------------------------------------------
        // Tier 2: Minutes 6-15
        // 1% per minute
        // ------------------------------------------

        if (delayMinutes > 5) {

            int secondTierMinutes =
                    Math.min(delayMinutes, 15) - 5;

            surgeFee +=
                    secondTierMinutes
                    * orderValue
                    * 0.01;
        }


        // ------------------------------------------
        // Tier 3: Minute 16 onwards
        // 2% per minute
        // ------------------------------------------

        if (delayMinutes > 15) {

            int thirdTierMinutes =
                    delayMinutes - 15;

            surgeFee +=
                    thirdTierMinutes
                    * orderValue
                    * 0.02;
        }


        // ------------------------------------------
        // Minimum surge floor
        // ------------------------------------------

        double minimumFee =
                orderValue
                * minimumSurgePercent
                / 100.0;


        // The floor applies only when delayed.
        surgeFee =
                Math.max(
                        surgeFee,
                        minimumFee
                );


        // Round to two decimal places.
        return Math.round(
                surgeFee * 100.0
        ) / 100.0;
    }


    public static void main(String[] args) {

        // Minimum surge floor = 1%
        ExamWeekSurgeFeeCalculator calculator =
                new ExamWeekSurgeFeeCalculator(1.0);


        System.out.println(
                "Delay 0: Rs "
                + calculator.calculateSurgeFee(
                        500,
                        0
                )
        );


        System.out.println(
                "Delay 1: Rs "
                + calculator.calculateSurgeFee(
                        500,
                        1
                )
        );


        System.out.println(
                "Delay 16: Rs "
                + calculator.calculateSurgeFee(
                        500,
                        16
                )
        );
    }
}