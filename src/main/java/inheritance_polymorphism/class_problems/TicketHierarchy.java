
package main.java.inheritance_polymorphism.class_problems;

public class Problem1TicketHierarchy {

    // Parent class
    static class EventTicket {

        private String attendeeId;
        protected double basePrice;
        protected double amountPaid;

        public EventTicket(String attendeeId, double basePrice) {

            if (attendeeId == null ||
                    attendeeId.trim().isEmpty() ||
                    attendeeId.trim().length() < 4) {

                throw new IllegalArgumentException(
                        "Invalid attendee ID"
                );
            }

            this.attendeeId = attendeeId.trim();
            this.basePrice = basePrice;
            this.amountPaid = 0;
        }

        public void pay(double amount) {

            if (amount <= 0) {
                System.out.println(
                        "Payment rejected: must be positive"
                );
                return;
            }

            amountPaid += amount;
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }
    }

    // Child class
    static class WorkshopTicket extends EventTicket {

        private String track;

        public WorkshopTicket(
                String attendeeId,
                double basePrice,
                String track) {

            super(attendeeId, basePrice);

            this.track = track;
        }
    }

    static String registerBatch(
            String[] attendeeIds,
            double basePrice) {

        int registered = 0;
        int rejected = 0;

        for (String attendeeId : attendeeIds) {

            try {
                new EventTicket(attendeeId, basePrice);
                registered++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered
                + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        // Test EventTicket
        try {
            new EventTicket("ST1", 500);
            System.out.println("Construction successful");

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Test WorkshopTicket
        WorkshopTicket w =
                new WorkshopTicket(
                        "STU2",
                        1200,
                        "AI/ML"
                );

        w.pay(500);

        System.out.println(
                "Workshop balance: "
                        + w.getBalanceDue()
        );

        // Test batch registration
        String[] attendees = {
                "STU1",
                "ST1",
                "STU2",
                " ",
                "STU3"
        };

        System.out.println(
                registerBatch(attendees, 500)
        );
    }
}
