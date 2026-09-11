package main.java.opp_constructors.assignment_problems;
class DeliveryAccount {

    private String studentId;
    private double orderValue;


    // Class-level state
    private static String kitchenName;
    private static int accountCount;


    // Static block for one-time class-level setup
    static {

        kitchenName = "Campus Central Kitchen";
        accountCount = 0;
    }


    // Full constructor
    public DeliveryAccount(
            String studentId,
            double orderValue) {

        if (studentId == null ||
            studentId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Student ID cannot be blank."
            );
        }

        if (orderValue < 0) {

            throw new IllegalArgumentException(
                    "Order value cannot be negative."
            );
        }

        this.studentId = studentId;
        this.orderValue = orderValue;

        accountCount++;
    }
    public DeliveryAccount(String studentId) {

        this(studentId, 0.0);
    }


    public String getStudentId() {

        return studentId;
    }


    public double getOrderValue() {

        return orderValue;
    }
    public final double calculateSurgeFee(
            int delayMinutes) {

        if (delayMinutes < 0) {

            throw new IllegalArgumentException(
                    "Delay minutes cannot be negative."
            );
        }


        if (delayMinutes == 0) {

            return 0.0;
        }


        double fee = 0.0;
        int firstTier =
                Math.min(delayMinutes, 5);

        fee +=
                firstTier
                * orderValue
                * 0.005;


        // Second tier: minutes 6-15
        if (delayMinutes > 5) {

            int secondTier =
                    Math.min(delayMinutes, 15) - 5;

            fee +=
                    secondTier
                    * orderValue
                    * 0.01;
        }


        // Third tier: minute 16 onwards
        if (delayMinutes > 15) {

            int thirdTier =
                    delayMinutes - 15;

            fee +=
                    thirdTier
                    * orderValue
                    * 0.02;
        }


        return Math.round(
                fee * 100.0
        ) / 100.0;
    }


    public void processRegularAccount(
            double amount,
            int delayMinutes) {

        double surgeFee =
                calculateSurgeFee(delayMinutes);

        System.out.println(
                studentId
                + " | Regular | Amount: Rs "
                + amount
                + " | Surge fee: Rs "
                + surgeFee
        );
    }
}
class PremiumDeliveryAccount
        extends DeliveryAccount {

    private double premiumCharge;


    public PremiumDeliveryAccount(
            String studentId,
            double orderValue) {

        super(studentId, orderValue);

        premiumCharge = 20.0;
    }


    public PremiumDeliveryAccount(
            String studentId) {

        super(studentId);

        premiumCharge = 20.0;
    }


    public void processPremiumAccount(
            double amount,
            int delayMinutes) {

        double surgeFee =
                calculateSurgeFee(delayMinutes);

        double totalFee =
                surgeFee + premiumCharge;

        System.out.println(
                getStudentId()
                + " | Premium | Amount: Rs "
                + amount
                + " | Surge fee: Rs "
                + surgeFee
                + " | Premium charge: Rs "
                + premiumCharge
                + " | Total fee: Rs "
                + totalFee
        );
    }
}
public class NightlyMultiKitchenReconciliation {
    public static void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        // Null safety
        if (account == null) {

            System.out.println(
                    "Null account skipped."
            );

            return;
        }


        if (account instanceof PremiumDeliveryAccount) {

            PremiumDeliveryAccount premium =
                    (PremiumDeliveryAccount) account;

            premium.processPremiumAccount(
                    amount,
                    delayMinutes
            );

        } else {

            account.processRegularAccount(
                    amount,
                    delayMinutes
            );
        }
    }


    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {
        if (accounts == null ||
            amounts == null ||
            delayMinutesArray == null) {

            System.out.println(
                    "Invalid batch: arrays cannot be null."
            );

            return;
        }
        if (accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length) {

            System.out.println(
                    "Batch rejected: array lengths do not match."
            );

            return;
        }


        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;

        double grandTotalSurgeFees = 0.0;
        for (int i = 0;
             i < accounts.length;
             i++) {

            DeliveryAccount account =
                    accounts[i];


            // Null entry
            if (account == null) {

                nullSkipped++;

                continue;
            }


            try {

                // Calculate surge fee for every
                // genuinely processed account.
                double surgeFee =
                        account.calculateSurgeFee(
                                delayMinutesArray[i]
                        );


                grandTotalSurgeFees += surgeFee;


                // Determine account type
                if (account instanceof PremiumDeliveryAccount) {

                    premiumCount++;

                } else {

                    regularCount++;
                }


                processAccount(
                        account,
                        amounts[i],
                        delayMinutesArray[i]
                );


                processed++;


            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Account at index "
                        + i
                        + " rejected: "
                        + e.getMessage()
                );
            }
        }

        System.out.println(
                "\nReconciliation Summary:"
        );


        System.out.println(
                processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + premiumCount
                + " premium | "
                + regularCount
                + " regular"
        );


        System.out.println(
                "Grand total surge fees = Rs "
                + grandTotalSurgeFees
        );
    }


    public static void main(String[] args) {

        // Mix of premium, null and regular accounts
        DeliveryAccount[] accounts = {

                new PremiumDeliveryAccount(
                        "STU001",
                        500
                ),

                null,

                new DeliveryAccount(
                        "STU002",
                        300
                )
        };


        double[] amounts = {

                500,
                400,
                300
        };


        int[] delayMinutesArray = {

                10,
                5,
                0
        };


        processBatch(
                accounts,
                amounts,
                delayMinutesArray
        );
    }
}