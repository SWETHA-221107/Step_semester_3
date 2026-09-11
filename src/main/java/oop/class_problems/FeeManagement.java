package main.java.oop.class_problems;
public class FeeManagement {

    // Parent class
    static class FeeAccount {

        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        void pay(double amount) {

            if (amount <= 0) {
                System.out.println("Payment rejected for " + regNo);
                return;
            }

            amountPaid += amount;
        }

        double getDue() {
            return totalFee - amountPaid;
        }
    }

    // Child class
    static class HostelFeeAccount extends FeeAccount {

        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        void payInTwoInstallments(double amount) {

            if (amount <= 0) {
                System.out.println("Invalid installment");
                return;
            }

            pay(amount);
            pay(amount);
        }
    }

    // Another child class
    static class ScholarshipFeeAccount extends FeeAccount {

        private double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee,
                              double amountPaid, double scholarshipPercent) {

            super(regNo, totalFee, amountPaid);

            this.scholarshipPercent = scholarshipPercent;
        }

        double effectiveDue() {

            double due = getDue();

            return due - (due * scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {

        FeeAccount plain =
                new FeeAccount("RA101", 150000, 150000);

        HostelFeeAccount hostel =
                new HostelFeeAccount("RA102", 200000, 60000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("RA103", 180000, 0, 20);

        // instanceof decides extra behaviour
        if (plain instanceof HostelFeeAccount) {
            ((HostelFeeAccount) plain).payInTwoInstallments(10000);
        }

        if (hostel instanceof HostelFeeAccount) {
            hostel.payInTwoInstallments(20000);
        }

        if (scholarship instanceof ScholarshipFeeAccount) {
            System.out.println("Scholarship account effective due: Rs "
                    + scholarship.effectiveDue());
        }

        System.out.println("Plain account due: Rs " + plain.getDue());
        System.out.println("Hostel account due: Rs " + hostel.getDue());
        System.out.println("Scholarship account effective due: Rs "
                + scholarship.effectiveDue());
    }
}