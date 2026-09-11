package main.java.oop.assigment_problems;
class BookIssue {

    private String title;
    private String borrowerName;
    private int daysOverdue;

    // Constructor
    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // Instance method to calculate fine
    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        } else {
            return 0;
        }
    }

    // Instance method to check severe overdue
    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // Static method to calculate total fine
    public static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }

    // Display book details
    public void displayStatus() {

        if (isSeverelyOverdue()) {
            System.out.println(title + " - "
                    + daysOverdue
                    + " days - Severely overdue");
        } else {
            System.out.println(title + " - "
                    + daysOverdue
                    + " days - OK");
        }
    }
}


public class LibraryFineSystem {

    public static void main(String[] args) {

        // Create an array of five BookIssue objects
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Ravi", 18),
            new BookIssue("Effective Java", "Anitha", 5),
            new BookIssue("Refactoring", "Karthik", 0),
            new BookIssue("DSA Handbook", "Meera", 21),
            new BookIssue("Design Patterns", "Suresh", 9)
        };

        // Print overdue status of every book
        for (BookIssue issue : issues) {
            issue.displayStatus();
        }
        double totalFine =
                BookIssue.totalFineCollected(issues);

        System.out.println("Total fine collected: Rs "
                + totalFine);
    }
}