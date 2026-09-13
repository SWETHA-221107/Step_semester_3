package main.java.inheritance_polymorphism.class_problems;

public class Problem5TicketSettlement {

    static class EventTicket {

        private static int ticketsIssued = 0;

        public final String ticketId;

        protected double basePrice;
        protected double amountPaid;

        public EventTicket(double basePrice) {

            // Increment counter first
            ticketsIssued++;

            // Create unique ticket ID
            ticketId = "TCK-" + (1000 + ticketsIssued);

            this.basePrice = basePrice;
            this.amountPaid = 0;
        }

        public void pay(double amount) {

            if (amount <= 0) {
                System.out.println(
                        "Payment rejected"
                );
                return;
            }

            amountPaid += amount;
        }

        public void pay(
                double amount,
                String mode) {

            System.out.println(
                    "Payment Mode: " + mode
            );

            // Reuse the one-argument version
            pay(amount);
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }

        public static boolean isValidPromoCode(
                String code) {

            // Check length BEFORE charAt()
            if (code == null ||
                    code.length() != 5) {

                return false;
            }

            // First character must be F
            if (code.charAt(0) != 'F') {
                return false;
            }

            // Characters 1, 2 and 3 must be digits
            if (!Character.isDigit(
                    code.charAt(1))) {
                return false;
            }

            if (!Character.isDigit(
                    code.charAt(2))) {
                return false;
            }

            if (!Character.isDigit(
                    code.charAt(3))) {
                return false;
            }

            // Last character must be uppercase
            if (!Character.isUpperCase(
                    code.charAt(4))) {
                return false;
            }

            return true;
        }

        public static int getTicketsIssued() {
            return ticketsIssued;
        }
    }

    static class GroupTicket
            extends EventTicket {

        private int groupSize;

        public GroupTicket(
                double basePrice,
                int groupSize) {

            super(basePrice);

            if (groupSize <= 0) {
                throw new IllegalArgumentException(
                        "Group size must be positive"
                );
            }

            this.groupSize = groupSize;
        }
    }

    static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (EventTicket ticket : tickets) {

            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket) {
                group++;
            } else {
                individual++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + group
                + " group | "
                + individual
                + " individual";
    }

    public static void main(String[] args) {

        EventTicket t1 =
                new EventTicket(500);

        System.out.println(
                "Ticket ID: "
                        + t1.ticketId
        );

        System.out.println(
                "Tickets issued: "
                        + EventTicket.getTicketsIssued()
        );

        // Promo code tests
        System.out.println(
                EventTicket.isValidPromoCode(
                        "F123A"
                )
        );

        System.out.println(
                EventTicket.isValidPromoCode(
                        "F12A"
                )
        );

        System.out.println(
                EventTicket.isValidPromoCode(
                        "X123A"
                )
        );

        // Payment
        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(
                "Balance Due: "
                        + t1.getBalanceDue()
        );

        // Nightly settlement
        EventTicket[] tickets = {
                new GroupTicket(2000, 5),
                null,
                new EventTicket(500)
        };

        System.out.println(
                processNightlySettlement(tickets)
        );
    }
}