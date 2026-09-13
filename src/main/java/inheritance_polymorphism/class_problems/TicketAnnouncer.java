package main.java.inheritance_polymorphism.class_problems;

public class TicketAnnouncer {

    static class EventTicket {

        protected double basePrice;

        public EventTicket(double basePrice) {
            this.basePrice = basePrice;
        }

        public double getBalanceDue() {
            return basePrice;
        }

        public String printTicket() {
            return "Standard | Balance: "
                    + getBalanceDue();
        }
    }

    static class WorkshopTicket
            extends EventTicket {

        private String track;

        public WorkshopTicket(
                double basePrice,
                String track) {

            super(basePrice);

            this.track = track;
        }

        public String getTrack() {
            return track;
        }

        @Override
        public String printTicket() {

            return "Workshop | Track: "
                    + track
                    + " | Balance: "
                    + getBalanceDue();
        }
    }

    static String batchPrint(
            EventTicket[] tickets) {

        StringBuilder report =
                new StringBuilder();

        for (EventTicket ticket : tickets) {

            // Polymorphic method call
            report.append(
                    ticket.printTicket()
            );

            // Safe downcasting
            if (ticket instanceof WorkshopTicket) {

                WorkshopTicket workshop =
                        (WorkshopTicket) ticket;

                report.append(
                        " [Track via downcast: "
                                + workshop.getTrack()
                                + "]"
                );
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        EventTicket standard =
                new EventTicket(500);

        WorkshopTicket workshop =
                new WorkshopTicket(
                        1200,
                        "AI/ML"
                );

        EventTicket[] tickets = {
                workshop
        };

        System.out.println(
                batchPrint(tickets)
        );
    }
}