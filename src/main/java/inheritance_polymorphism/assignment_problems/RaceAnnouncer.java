package main.java.inheritance_polymorphism.assignment_problems;

public class RaceAnnouncer {

    static class RaceEntry {

        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(
                String bibNumber,
                double entryFee) {

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public String announce() {

            return "Race Entry | Bib: "
                    + bibNumber
                    + " | Balance: "
                    + getBalanceDue();
        }
    }

    static class RunnerEntry
            extends RaceEntry {

        private String category;

        public RunnerEntry(
                String bibNumber,
                double entryFee,
                String category) {

            super(bibNumber, entryFee);

            this.category = category;
        }

        @Override
        public String announce() {

            return "Runner Entry | Bib: "
                    + bibNumber
                    + " | Category: "
                    + category
                    + " | Balance: "
                    + getBalanceDue();
        }
    }

    static class RelayTeamEntry
            extends RaceEntry {

        private int teamSize;

        public RelayTeamEntry(
                String bibNumber,
                double entryFee,
                int teamSize) {

            super(bibNumber, entryFee);

            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }

        @Override
        public String announce() {

            return "Relay Team | Bib: "
                    + bibNumber
                    + " | Team Size: "
                    + teamSize
                    + " | Balance: "
                    + getBalanceDue();
        }
    }

    static String announceAll(
            RaceEntry[] entries) {

        StringBuilder report =
                new StringBuilder();

        for (RaceEntry entry : entries) {

            // Polymorphic call
            report.append(
                    entry.announce()
            );

            // Safe downcasting
            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entry;

                report.append(
                        " [Team size via downcast: "
                                + relay.getTeamSize()
                                + "]"
                );
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        RunnerEntry runner =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        RelayTeamEntry relay =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        RaceEntry[] fleet = {
                runner,
                relay
        };

        System.out.println(
                announceAll(fleet)
        );
    }
}