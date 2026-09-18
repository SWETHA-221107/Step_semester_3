package main.java.inheritance_polymorphism.assignment_problems;

public class LateWithdrawalPenalty {

    static class RaceEntry {

        protected double entryFee;
        protected double amountPaid;

        private double[] lateFeeHistory;
        private int feeCount;

        public RaceEntry(
                String bibNumber,
                double entryFee) {

            this.entryFee = entryFee;
            this.amountPaid = 0;

            lateFeeHistory = new double[10];
            feeCount = 0;
        }

        public void pay(double amount) {

            if (amount > 0) {
                amountPaid += amount;
            }
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        protected void applyLateFee(
                double amount) {

            amountPaid -= amount;

            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }

        public double[] getLateFeeHistory() {

            double[] copy =
                    new double[feeCount];

            for (int i = 0; i < feeCount; i++) {

                copy[i] = lateFeeHistory[i];
            }

            return copy;
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
        protected void applyLateFee(
                double amount) {

            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(
                r.getBalanceDue()
        );

        double[] history =
                r.getLateFeeHistory();

        System.out.println(
                history[0]
        );

        // Try to modify the returned array
        history[0] = 999;

        // Original internal array is unchanged
        System.out.println(
                r.getLateFeeHistory()[0]
        );
    }
}