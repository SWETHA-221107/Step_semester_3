package main.java.access_modifiers_encapsulation.assignment_problems;

public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static {
        System.out.println("Loan receipt system initialized.");
    }

    public LoanReceipt(
            String memberId,
            String[] bookIds) {

        if (memberId == null || bookIds == null) {
            throw new IllegalArgumentException(
                    "Invalid receipt data"
            );
        }

        for (String id : bookIds) {

            if (id == null ||
                    !id.matches("BK-\\d{3}")) {

                throw new IllegalArgumentException(
                        "Invalid book ID: " + id
                );
            }
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (newId == null ||
                !newId.matches("BK-\\d{3}")) {

            throw new IllegalArgumentException(
                    "Invalid book ID"
            );
        }

        String[] newBookIds = bookIds.clone();

        newBookIds[index] = newId;

        return new LoanReceipt(
                memberId,
                newBookIds
        );
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt regular =
                new LoanReceipt(
                        "LIB-002",
                        new String[]{
                                "BK-201"
                        }
                );

        ReferenceOnlyLoanReceipt reference =
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{
                                "BK-200"
                        },
                        "Reading Room 3"
                );

        LoanReceipt[] receipts = {
                reference,
                null,
                regular
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );

        String[] ids = regular.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
                regular.getBookIds()[0]
        );
    }
}

class ReferenceOnlyLoanReceipt
        extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }
}
