package main.java.abstract_design_patterns.assignment_problems;

import java.util.*;

interface ShippingType {
    double charge(double weight);
    String name();
}

class StandardShipping implements ShippingType {
    public double charge(double weight) {
        return 40 + 10 * weight;
    }
    public String name() { return "Standard"; }
}

class ExpressShipping implements ShippingType {
    public double charge(double weight) {
        return 80 + 15 * weight;
    }
    public String name() { return "Express"; }
}

class FragileShipping implements ShippingType {
    public double charge(double weight) {
        return 40 + 10 * weight + 50;
    }
    public String name() { return "Fragile"; }
}

interface NotificationChannel {
    void notifyStatus(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {
    public void notifyStatus(String id, String status) {
        System.out.println("[SMS] " + id + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notifyStatus(String id, String status) {
        System.out.println("[Email] " + id + " is now " + status + ".");
    }
}

class Parcel {
    enum Status {
        BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
    }

    private final String id;
    private final double weight;
    private final ShippingType shipping;
    private Status status = Status.BOOKED;
    private final List<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, double weight, ShippingType shipping) {
        if (weight <= 0) throw new IllegalArgumentException("Weight must be positive.");
        this.id = id;
        this.weight = weight;
        this.shipping = shipping;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    private void notifyAllChannels() {
        for (NotificationChannel channel : channels) {
            channel.notifyStatus(id, status.name());
        }
    }

    public void book() {
        System.out.printf("Parcel %s booked (%s, %.1f kg). Charge: ₹%.2f%n",
                id, shipping.name(), weight, shipping.charge(weight));
        notifyAllChannels();
    }

    public void updateStatus(Status next) {
        Status[] sequence = {
            Status.BOOKED, Status.PICKED_UP, Status.IN_TRANSIT,
            Status.OUT_FOR_DELIVERY, Status.DELIVERED
        };

        int currentIndex = Arrays.asList(sequence).indexOf(status);
        int nextIndex = Arrays.asList(sequence).indexOf(next);

        if (currentIndex < 0 || nextIndex != currentIndex + 1) {
            System.out.println("Invalid transition: " + status
                    + " → " + next + " is not allowed.");
            return;
        }

        status = next;
        notifyAllChannels();
    }

    public void cancel() {
        if (status != Status.BOOKED) {
            System.out.println("Cancellation failed: " + id
                    + " can be cancelled only while BOOKED.");
            return;
        }

        status = Status.CANCELLED;
        notifyAllChannels();
    }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {
        Parcel parcel = new Parcel("P101", 2, new ExpressShipping());

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        parcel.book();
        parcel.updateStatus(Parcel.Status.PICKED_UP);
        parcel.cancel();
        parcel.updateStatus(Parcel.Status.IN_TRANSIT);
        parcel.updateStatus(Parcel.Status.DELIVERED);
    }
}