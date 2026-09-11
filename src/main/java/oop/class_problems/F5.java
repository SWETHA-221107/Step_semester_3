package main.java.oop.class_problems;
// FeeAccount class
class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    // Constructor
    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    // Method to pay fee
    public void pay(double amount) {

        if (amount <= 0) {
            System.out.println("Payment rejected: Amount must be positive.");
        } else {
            amountPaid += amount;
            System.out.println("Payment successful: Rs." + amount);
        }
    }

    // Calculate remaining fee
    public double getDue() {
        return totalFee - amountPaid;
    }

    public String getRegNo() {
        return regNo;
    }
}


// Hostel Fee Account
class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    // Pay fee in two installments
    public void payInTwoInstallments(double amount) {

        if (amount <= 0) {
            System.out.println("Installment rejected: Amount must be positive.");
        } else {

            double installment = amount / 2;

            pay(installment);
            pay(installment);

            System.out.println("Paid in two installments.");
        }
    }
}


// Hostel Room class
class HostelRoom {

    private String roomNo;
    private int beds;
    private int occupied;

    // Constructor
    HostelRoom(String roomNo, int beds) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = 0;
    }

    // Allot a student to the room
    public void allot(String name) {

        if (occupied < beds) {
            occupied++;

            System.out.println(name
                    + " allotted to room "
                    + roomNo);
        } else {
            System.out.println("Room "
                    + roomNo
                    + " is full.");
        }
    }

    // Check whether room has an available bed
    public boolean isAvailable() {
        return occupied < beds;
    }

    public String getRoomNo() {
        return roomNo;
    }
}


// Student class
class SrmStudent {

    private String name;
    private String regNo;

    private HostelFeeAccount feeAccount;
    private HostelRoom room;

    // Static variable shared by all students
    private static int totalStudents = 0;

    // Constructor
    SrmStudent(String name, String regNo,
               HostelFeeAccount feeAccount) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;

        totalStudents++;
    }

    // Assign hostel room
    public void setRoom(HostelRoom room) {
        this.room = room;
    }

    // Display complete student status
    public void fullStatus() {

        System.out.println("Name: " + name);
        System.out.println("Register Number: " + regNo);

        System.out.println("Fee Due: Rs."
                + feeAccount.getDue());

        if (room != null) {
            System.out.println("Room Number: "
                    + room.getRoomNo());
        } else {
            System.out.println("Room Number: unallotted");
        }

        System.out.println();
    }

    // Static method to get total students
    public static int getTotalStudents() {
        return totalStudents;
    }
}


// Main class
public class F5 {

    public static void main(String[] args) {

        // Create fee accounts
        HostelFeeAccount raviFee =
                new HostelFeeAccount(
                        "RA231100301011",
                        150000,
                        0
                );

        HostelFeeAccount anithaFee =
                new HostelFeeAccount(
                        "RA231100301012",
                        200000,
                        0
                );

        HostelFeeAccount karthikFee =
                new HostelFeeAccount(
                        "RA231100301013",
                        200000,
                        0
                );


        // Create students
        SrmStudent ravi =
                new SrmStudent(
                        "Ravi",
                        "RA231100301011",
                        raviFee
                );

        SrmStudent anitha =
                new SrmStudent(
                        "Anitha",
                        "RA231100301012",
                        anithaFee
                );

        SrmStudent karthik =
                new SrmStudent(
                        "Karthik",
                        "RA231100301013",
                        karthikFee
                );


        // Make valid payment for Ravi
        raviFee.pay(10000);


        // Invalid payment for Anitha
        anithaFee.pay(-5000);

        // Valid payment for Anitha
        anithaFee.pay(20000);


        // Create hostel rooms
        HostelRoom room1 =
                new HostelRoom("C-214", 1);

        HostelRoom room2 =
                new HostelRoom("C-507", 1);


        // Allot rooms to two students
        room1.allot("Ravi");
        ravi.setRoom(room1);

        room2.allot("Anitha");
        anitha.setRoom(room2);


        // Karthik does not get a room
        // Therefore his room remains null


        // Display student status
        System.out.println("\n----- STUDENT STATUS -----");

        ravi.fullStatus();
        anitha.fullStatus();
        karthik.fullStatus();


        // Display total number of students
        System.out.println("Total Students: "
                + SrmStudent.getTotalStudents());
    }
}