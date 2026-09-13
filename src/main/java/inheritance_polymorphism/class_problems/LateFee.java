package main.java.inheritance_polymorphism.class_problems;

public class Problem3LateFee {

    static class EventTicket {

        protected double basePrice;
        protected double amountPaid;

        private double[] lateFeeHistory;
        private int feeCount;

        public EventTicket(double basePrice) {

            this.basePrice = basePrice;
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
            return basePrice - amountPaid;
        }

        protected void applyLateFee(double amount) {

            amountPaid -= amount;

            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }

        public double[] getLateFeeHistory() {

            double[] result =
                    new double[feeCount];

            for (int i = 0; i < feeCount; i++) {
                result[i] = lateFeeHistory[i];
            }

            return result;
        }
    }

    static class WorkshopTicket
            extends EventTicket {

        public WorkshopTicket(double basePrice) {
            super(basePrice);
        }

        @Override
        protected void applyLateFee(double amount) {

            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {

        WorkshopTicket w =
                new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
                "Balance Due: "
                        + w.getBalanceDue()
        );

        double[] history =
                w.getLateFeeHistory();

        System.out.println(
                "History before modification:"
        );

        System.out.println(history[0]);

        // Try to modify returned array
        history[0] = 999;

        System.out.println(
                "Actual history after modification:"
        );

        System.out.println(
                w.getLateFeeHistory()[0]
        );
    }
}