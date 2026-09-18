package main.java.inheritance_polymorphism.assignment_problems;

public class RaceSettlement {

    // =========================
    // RaceEntry
    // =========================
    static class RaceEntry {

        private static int bibCounter = 0;

        private String bibNumber;
        private double entryFee;
        private double amountPaid;

        public final String entryCode;

        public RaceEntry(String bibNumber, double entryFee) {

            if (bibNumber == null || bibNumber.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }

            if (entryFee <= 0) {
                throw new IllegalArgumentException("Entry fee must be positive");
            }

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0;

            // Increment counter inside constructor
            bibCounter++;

            // Final entry code
            this.entryCode = "RACE-" + bibCounter;
        }

        public void pay(double amount) {

            if (amount <= 0) {
                return;
            }

            amountPaid += amount;
        }

        // Overloaded pay method
        public void pay(double amount, String mode) {

            System.out.println("Paying via " + mode);

            // Reuse the normal pay method
            pay(amount);
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public String getBibNumber() {
            return bibNumber;
        }

        public double getEntryFee() {
            return entryFee;
        }

        public static int getBibCounter() {
            return bibCounter;
        }
    }


    // =========================
    // RunnerEntry
    // =========================
    static class RunnerEntry extends RaceEntry {

        private String category;

        public RunnerEntry(
                String bibNumber,
                double entryFee,
                String category) {

            super(bibNumber, entryFee);
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }


    // =========================
    // EliteRunnerEntry
    // =========================
    static class EliteRunnerEntry extends RunnerEntry {

        private double sponsorBonus;

        public EliteRunnerEntry(
                String bibNumber,
                double entryFee,
                String category,
                double sponsorBonus) {

            super(bibNumber, entryFee, category);
            this.sponsorBonus = sponsorBonus;
        }

        public double getSponsorBonus() {
            return sponsorBonus;
        }
    }


    // =========================
    // RelayTeamEntry
    // =========================
    static class RelayTeamEntry extends RaceEntry {

        private int teamSize;

        public RelayTeamEntry(
                String bibNumber,
                double entryFee,
                int teamSize) {

            super(bibNumber, entryFee);

            if (teamSize <= 0) {
                throw new IllegalArgumentException(
                        "Team size must be positive");
            }

            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }
    }


    // =========================
    // Discount Code Validation
    // =========================
    public static boolean isValidDiscountCode(String code) {

        // Check length BEFORE charAt()
        if (code == null || code.length() != 5) {
            return false;
        }

        // Must start with M
        if (code.charAt(0) != 'M') {
            return false;
        }

        // Next three characters must be digits
        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        // Last character must be uppercase
        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }


    // =========================
    // Nightly Settlement
    // =========================
    public static String settleNight(RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relay + " relay | "
                + individual + " individual";
    }


    // =========================
    // Main Method
    // =========================
    public static void main(String[] args) {

        // Discount code tests
        System.out.println(
                "M123A: " + isValidDiscountCode("M123A")
        );

        System.out.println(
                "M12A: " + isValidDiscountCode("M12A")
        );

        System.out.println(
                "X123A: " + isValidDiscountCode("X123A")
        );


        // Create entries
        RaceEntry entry1 =
                new RaceEntry("BIB1001", 100);

        RunnerEntry runner =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        EliteRunnerEntry elite =
                new EliteRunnerEntry(
                        "BIB3001",
                        150,
                        "Elite Full Marathon",
                        500
                );

        RelayTeamEntry relay =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );


        // Normal payment
        runner.pay(20);

        // Overloaded payment
        runner.pay(10, "UPI");


        // Settlement
        RaceEntry[] entries = {
                elite,
                null,
                relay
        };

        System.out.println(
                RaceSettlement.settleNight(entries)
        );


        // Counter
        System.out.println(
                "Bib Counter: " + RaceEntry.getBibCounter()
        );
    }
}