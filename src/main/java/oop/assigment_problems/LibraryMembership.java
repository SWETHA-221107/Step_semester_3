package main.java.oop.assigment_problems;



// ==========================================
// BROKEN VERSION
// ==========================================

class BrokenLibraryMember {

    // WRONG: These fields should NOT be static.

    // name is different for every member,
    // so it should belong to each object.
    static String name;

    // memberId is different for every member,
    // so it should belong to each object.
    static String memberId;

    // booksIssued is different for every member,
    // so it should belong to each object.
    static int booksIssued;


    // Constructor
    BrokenLibraryMember(
            String name,
            String memberId,
            int booksIssued) {

        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}


// ==========================================
// FIXED VERSION
// ==========================================

class LibraryMember {

    // These are instance fields because every
    // library member has different values.
    private String name;
    private String memberId;
    private int booksIssued;


    // These are static because they belong
    // to the library as a whole.
    private static String libraryName =
            "SRM Central Library";

    private static int memberCount = 0;


    // Constructor
    LibraryMember(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        // Automatically generate member ID
        this.memberId =
                String.format(
                        "LM-%04d",
                        1000 + memberCount
                );
    }


    // Instance method
    public void printMemberCard() {

        System.out.println(
                name
                + " | "
                + memberId
        );
    }


    // Static method
    public static void printTotalMembers() {

        System.out.println(
                "Total members: "
                + memberCount
        );
    }
}


// ==========================================
// MAIN CLASS
// ==========================================

public class LibraryMembership {

    public static void main(String[] args) {


        // ==========================================
        // BROKEN VERSION
        // ==========================================

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember(
                        "Aditi",
                        "LM-1001",
                        2
                );

        BrokenLibraryMember member2 =
                new BrokenLibraryMember(
                        "Rohan",
                        "LM-1002",
                        3
                );


        // Both show Rohan because the fields are static.
        System.out.println(
                BrokenLibraryMember.name
        );

        System.out.println(
                BrokenLibraryMember.name
        );


        System.out.println(
                "(Aditi's data was overwritten)"
        );


        // ==========================================
        // FIXED VERSION
        // ==========================================

        System.out.println("\nFixed version:");

        LibraryMember m1 =
                new LibraryMember(
                        "Aditi",
                        2
                );

        LibraryMember m2 =
                new LibraryMember(
                        "Rohan",
                        3
                );


        // Each object keeps its own data.
        m1.printMemberCard();
        m2.printMemberCard();


        // Static method called using class name
        LibraryMember.printTotalMembers();
    }
}
