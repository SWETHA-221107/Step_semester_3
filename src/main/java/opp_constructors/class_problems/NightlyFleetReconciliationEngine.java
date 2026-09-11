package main.java.opp_constructors.class_problems;
class BusTicketAccount {

    private String bookingId;
    private double ticketFare;

    // Class-level state
    private static String depotName;

    private static int accountCount;


    // Static block
    static {

        depotName = "Central Bus Depot";
        accountCount = 0;
    }


    // Full constructor
    public BusTicketAccount(
            String bookingId,
            double ticketFare) {

        if (bookingId == null ||
            bookingId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Booking ID cannot be blank."
            );
        }

        if (ticketFare < 0) {

            throw new IllegalArgumentException(
                    "Ticket fare cannot be negative."
            );
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;

        accountCount++;
    }


    // Provisional constructor
    public BusTicketAccount(String bookingId) {

        this(bookingId, 0.0);
    }


    public double getTicketFare() {
        return ticketFare;
    }


    public String getBookingId() {
        return bookingId;
    }


    // Final penalty calculation
    // Reusing a simple flat-rate version of Problem 4.
    public final double calculatePenalty(
            int minutesLate) {

        if (minutesLate < 0) {

            throw new IllegalArgumentException(
                    "Minutes late cannot be negative."
            );
        }

        // Simple flat penalty:
        // 1% of fare for each late minute.
        double penalty =
                ticketFare
                * 0.01
                * minutesLate;

        return Math.round(
                penalty * 100.0
        ) / 100.0;
    }


    public void processAccount(
            double amount,
            int minutesLate) {

        double penalty =
                calculatePenalty(minutesLate);

        System.out.println(
                bookingId
                + " | Amount: Rs "
                + amount
                + " | Penalty: Rs "
                + penalty
        );
    }
}

class SleeperBusTicketAccount
        extends BusTicketAccount {

    public SleeperBusTicketAccount(
            String bookingId,
            double ticketFare) {

        super(bookingId, ticketFare);
    }


    public SleeperBusTicketAccount(
            String bookingId) {

        super(bookingId);
    }


    // Sleeper accounts settle with a fixed
    // additional sleeper service charge.
    public void sleeperSettlement(
            double amount,
            int minutesLate) {

        double penalty =
                calculatePenalty(minutesLate);

        double sleeperCharge = 100.0;

        double total =
                amount
                + penalty
                + sleeperCharge;

        System.out.println(
                getBookingId()
                + " | Sleeper settlement: Rs "
                + total
        );
    }
}

public class NightlyFleetReconciliationEngine {

    public static void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account == null) {

            System.out.println(
                    "Null account skipped."
            );

            return;
        }


        if (account instanceof SleeperBusTicketAccount) {

            SleeperBusTicketAccount sleeper =
                    (SleeperBusTicketAccount) account;

            sleeper.sleeperSettlement(
                    amount,
                    minutesLate
            );

        } else {

            account.processAccount(
                    amount,
                    minutesLate
            );
        }
    }


    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {


        if (accounts == null ||
            amounts == null ||
            minutesLateArray == null) {

            System.out.println(
                    "Invalid batch: arrays cannot be null."
            );

            return;
        }


        // If lengths differ, process only the common
        // valid range. This prevents incorrect
        // amount/account pairing and array errors.
        int limit =
                Math.min(
                        accounts.length,
                        Math.min(
                                amounts.length,
                                minutesLateArray.length
                        )
                );


        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;

        double grandTotalPenalty = 0.0;


        for (int i = 0; i < limit; i++) {

            BusTicketAccount account =
                    accounts[i];


            if (account == null) {

                nullSkipped++;
                continue;
            }


            try {

                double penalty =
                        account.calculatePenalty(
                                minutesLateArray[i]
                        );

                grandTotalPenalty += penalty;

                processed++;


                if (account instanceof SleeperBusTicketAccount) {

                    sleeperCount++;

                } else {

                    regularCount++;
                }


                processAccount(
                        account,
                        amounts[i],
                        minutesLateArray[i]
                );


            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Invalid account at index "
                        + i
                        + ": "
                        + e.getMessage()
                );
            }
        }


        System.out.println("\nReconciliation Summary:");

        System.out.println(
                processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + sleeperCount
                + " sleeper | "
                + regularCount
                + " regular"
        );

        System.out.println(
                "Grand total penalties = Rs "
                + grandTotalPenalty
        );
    }


    public static void main(String[] args) {

        BusTicketAccount[] accounts = {

            new SleeperBusTicketAccount(
                    "BK001",
                    2000
            ),

            null,

            new BusTicketAccount(
                    "BK002",
                    1200
            )
        };


        double[] amounts = {

            1200,
            900,
            700
        };


        int[] minutesLateArray = {

            10,
            5,
            0
        };


        processBatch(
                accounts,
                amounts,
                minutesLateArray
        );
    }
}
