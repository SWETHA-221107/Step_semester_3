package main.java.abstract_design_patterns.class_problems;
abstract class Vehicle {
    protected String name;
    private boolean available = true;

    public Vehicle(String name) {
        this.name = name;
    }

    public abstract double calculateCharge(int days);

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }
}

class StandardCar extends Vehicle {

    public StandardCar(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {

    public LuxuryCar(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100;
    }
}

class SUV extends Vehicle {

    public SUV(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Rental {
    private Vehicle vehicle;
    private int days;
    private double totalCharge;

    public Rental(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
        this.totalCharge = vehicle.calculateCharge(days);
    }

    public void displayRental() {
        System.out.printf(
                "%s rented for %d days. Total charge: $%.2f%n",
                vehicle.getName(),
                days,
                totalCharge
        );
    }
}

class RentalService {

    public Rental rentVehicle(Vehicle vehicle, int days) {

        if (!vehicle.isAvailable()) {
            System.out.println(
                    vehicle.getName() + " is not available."
            );
            return null;
        }

        if (days <= 0) {
            System.out.println("Rental duration must be positive.");
            return null;
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(vehicle, days);
        rental.displayRental();

        return rental;
    }

    public void returnVehicle(Vehicle vehicle) {
        vehicle.setAvailable(true);

        System.out.println(
                vehicle.getName() + " returned. Now available."
        );
    }
}

public class VehicleRentalSystem {

    public static void main(String[] args) {

        RentalService service = new RentalService();

        Vehicle luxuryCar = new LuxuryCar("Luxury Car A");
        Vehicle standardCar = new StandardCar("Standard Car B");

        Rental rental1 = service.rentVehicle(luxuryCar, 3);

        Rental rental2 = service.rentVehicle(standardCar, 5);

        service.rentVehicle(luxuryCar, 2);

        service.returnVehicle(luxuryCar);

        service.rentVehicle(luxuryCar, 2);
    }
}