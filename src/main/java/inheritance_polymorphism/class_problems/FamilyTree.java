package main.java.inheritance_polymorphism.class_problems;

public class FamilyTree {

    // Base class
    static class EventTicket {

        protected String attendeeId;
        protected double basePrice;
        protected double amountPaid;

        public EventTicket(
                String attendeeId,
                double basePrice) {

            this.attendeeId = attendeeId;
            this.basePrice = basePrice;
            this.amountPaid = 0;
        }

        public void pay(double amount) {

            if (amount > 0) {
                amountPaid += amount;
            }
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }

        public void printTicket() {

            System.out.println(
                    "Standard Event Ticket | Balance Due: "
                            + getBalanceDue()
            );
        }
    }

    // Single inheritance
    static class WorkshopTicket extends EventTicket {

        protected String track;

        public WorkshopTicket(
                String attendeeId,
                double basePrice,
                String track) {

            super(attendeeId, basePrice);

            this.track = track;
        }

        @Override
        public void printTicket() {

            System.out.println(
                    "Workshop Ticket | Track: "
                            + track
                            + " | Balance Due: "
                            + getBalanceDue()
            );
        }
    }

    // Multilevel inheritance
    static class PremiumWorkshopTicket
            extends WorkshopTicket {

        private double kitFee;

        public PremiumWorkshopTicket(
                String attendeeId,
                double basePrice,
                String track,
                double kitFee) {

            super(attendeeId, basePrice, track);

            this.kitFee = kitFee;
        }

        @Override
        public void printTicket() {

            System.out.println(
                    "Premium Workshop Ticket | Track: "
                            + track
                            + " | Kit Fee: "
                            + kitFee
                            + " | Balance Due: "
                            + getBalanceDue()
            );
        }
    }

    // Hierarchical inheritance
    static class HackathonTicket
            extends EventTicket {

        private String teamName;

        public HackathonTicket(
                String attendeeId,
                double basePrice,
                String teamName) {

            super(attendeeId, basePrice);

            this.teamName = teamName;
        }

        @Override
        public void printTicket() {

            System.out.println(
                    "Hackathon Ticket | Team: "
                            + teamName
                            + " | Balance Due: "
                            + getBalanceDue()
            );
        }
    }

    static String classifyGeneration(
            EventTicket ticket) {

        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        }

        if (ticket instanceof WorkshopTicket) {
            return "Single inheritance child";
        }

        return "Base EventTicket";
    }

    static double getTotalBalanceDue(
            EventTicket[] tickets) {

        double total = 0;

        for (EventTicket ticket : tickets) {
            total += ticket.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        EventTicket standardTicket =
                new EventTicket("STU1", 500);

        WorkshopTicket workshopTicket =
                new WorkshopTicket(
                        "STU2",
                        1200,
                        "AI/ML"
                );

        PremiumWorkshopTicket premiumTicket =
                new PremiumWorkshopTicket(
                        "STU3",
                        2000,
                        "Cloud Native",
                        300
                );

        HackathonTicket hackathonTicket =
                new HackathonTicket(
                        "STU4",
                        800,
                        "Byte Force"
                );

        // Polymorphic printTicket()
        standardTicket.printTicket();
        workshopTicket.printTicket();
        premiumTicket.printTicket();
        hackathonTicket.printTicket();

        System.out.println();

        // instanceof classification
        System.out.println(
                classifyGeneration(premiumTicket)
        );

        System.out.println(
                classifyGeneration(hackathonTicket)
        );

        System.out.println();

        // Polymorphic balance calculation
        EventTicket[] tickets = {
                standardTicket,
                workshopTicket,
                premiumTicket,
                hackathonTicket
        };

        System.out.println(
                "Total Balance Due: "
                        + getTotalBalanceDue(tickets)
        );
    }
}