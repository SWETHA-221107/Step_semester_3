package main.java.oop.class_problems;
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
            System.out.println("Payment of Rs." + amount + " successful.");
        }
    }

    // Method to calculate remaining fee
    public double getDue() {
        return totalFee - amountPaid;
    }

    public String getRegNo() {
        return regNo;
    }
}


// Child class for Hostel Fee
class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    // Pays the given amount in two equal installments
    public void payInTwoInstallments(double amount) {
        if (amount <= 0) {
            System.out.println("Installment rejected: Amount must be positive.");
        } else {
            double installment = amount / 2;

            pay(installment);
            pay(installment);

            System.out.println("Paid in two installments of Rs." + installment);
        }
    }
}


// Child class for Scholarship Fee
class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double amountPaid, double scholarshipPercent) {

        super(regNo, totalFee, amountPaid);

        if (scholarshipPercent >= 0 && scholarshipPercent <= 100) {
            this.scholarshipPercent = scholarshipPercent;
        } else {
            this.scholarshipPercent = 0;
            System.out.println("Invalid scholarship percentage.");
        }
    }

    // Calculates due amount after scholarship
    public double effectiveDue() {
        double due = getDue();

        double discount = due * scholarshipPercent / 100;

        return due - discount;
    }
}


// Main class
public class F2 {

    public static void main(String[] args) {

        // Normal fee account
        FeeAccount normalAccount =
                new FeeAccount("RA231100301011", 150000, 150000);

        // Hostel fee account
        HostelFeeAccount hostelAccount =
                new HostelFeeAccount("RA231100301012", 200000, 60000);

        // Scholarship fee account
        ScholarshipFeeAccount scholarshipAccount =
                new ScholarshipFeeAccount("RA231100301013",
                        180000, 0, 20);


        // Display normal account
        System.out.println("Normal Fee Account");
        System.out.println("Register Number: "
                + normalAccount.getRegNo());
        System.out.println("Outstanding Due: Rs."
                + normalAccount.getDue());


        // instanceof is used to check special child-class behavior
        if (hostelAccount instanceof HostelFeeAccount) {

            System.out.println("\nHostel Fee Account");
            System.out.println("Register Number: "
                    + hostelAccount.getRegNo());

            // Demonstrating installment payment
            // Comment this line if you want the initial due of Rs.140000
            // to remain unchanged.
            // hostelAccount.payInTwoInstallments(20000);

            System.out.println("Outstanding Due: Rs."
                    + hostelAccount.getDue());
        }


        // Scholarship account
        if (scholarshipAccount instanceof ScholarshipFeeAccount) {

            System.out.println("\nScholarship Fee Account");
            System.out.println("Register Number: "
                    + scholarshipAccount.getRegNo());

            System.out.println("Outstanding Due After Scholarship: Rs."
                    + scholarshipAccount.effectiveDue());
        }
    }
}