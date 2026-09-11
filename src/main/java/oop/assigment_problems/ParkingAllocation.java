package main.java.oop.assigment_problems;
// ParkingSlot class
class ParkingSlot {

    private String slotNo;
    private int capacity;
    private int occupiedCount;

    // Constructor
    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // Allot a parking slot
    public void allot(String vehicleNo) {

        if (occupiedCount < capacity) {

            occupiedCount++;

            System.out.println(
                    vehicleNo
                    + " allotted to slot "
                    + slotNo
            );

        } else {

            System.out.println(
                    "Slot "
                    + slotNo
                    + " is full."
            );
        }
    }

    // Check whether slot is available
    public boolean isAvailable() {
        return occupiedCount < capacity;
    }
}


// Main class
public class ParkingAllocation {

    // Find the first available parking slot
    public static ParkingSlot findAvailableSlot(
            ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot != null && slot.isAvailable()) {
                return slot;
            }
        }

        return null;
    }


    // Safely allot a parking slot
    public static void safeAllot(
            ParkingSlot[] slots,
            String vehicleNo) {

        ParkingSlot availableSlot =
                findAvailableSlot(slots);

        // Check for null before using the object
        if (availableSlot != null) {

            availableSlot.allot(vehicleNo);

        } else {

            System.out.println(
                    "No slots available for "
                    + vehicleNo
            );
        }
    }


    public static void main(String[] args) {

        // First case:
        // A1 has one free space.
        ParkingSlot[] slots = {

            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };


        System.out.println("First case:");

        safeAllot(
                slots,
                "TN09AB1234"
        );


        // Second case:
        // Every slot is full.
        ParkingSlot[] fullSlots = {

            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };


        System.out.println("\nSecond case:");

        safeAllot(
                fullSlots,
                "TN09AB1234"
        );


        /*
         * The ParkingSlot[] array stores references to ParkingSlot
         * objects. Passing the array to a method passes the array
         * reference, not copies of all the ParkingSlot objects.
         * Therefore, changes made through a slot reference can
         * affect the original objects.
         */
    }
}