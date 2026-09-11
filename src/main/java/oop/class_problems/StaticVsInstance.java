package main.java.oop.class_problems;
public class StaticVsInstance {

    // ==============================
    // BROKEN VERSION
    // ==============================

    static class BrokenStudent {

        // WRONG: These should not be static.
        // name is different for every student.
        // regNo is different for every student.
        // attendance is different for every student.

        static String name;
        static String regNo;
        static int attendance;

        BrokenStudent(String name, String regNo, int attendance) {
            BrokenStudent.name = name;
            BrokenStudent.regNo = regNo;
            BrokenStudent.attendance = attendance;
        }
    }


    // ==============================
    // FIXED VERSION
    // ==============================

    static class SrmStudent {

        // Instance fields:
        // Every student has separate values.
        String name;
        String regNo;
        int attendance;

        // Static fields:
        // Shared by all students.
        static String university = "SRM Institute of Science and Technology";
        static int admissionCount = 0;

        SrmStudent(String name, int attendance) {

            this.name = name;
            this.attendance = attendance;

            admissionCount++;

            // Automatically generate registration number
            this.regNo = "RA2311003010"
                    + String.format("%02d", admissionCount);
        }

        void printIdCard() {

            System.out.println(name + " | " + regNo);
        }

        static void printTotalAdmissions() {

            System.out.println("Students admitted so far: "
                    + admissionCount);
        }
    }


    public static void main(String[] args) {

        // ==============================
        // BROKEN VERSION
        // ==============================

        System.out.println("Broken version:");

        BrokenStudent student1 =
                new BrokenStudent("Ravi", "RA101", 82);

        BrokenStudent student2 =
                new BrokenStudent("Meera", "RA102", 90);

        System.out.println(BrokenStudent.name);
        System.out.println(BrokenStudent.name);

        System.out.println();


        // ==============================
        // FIXED VERSION
        // ==============================

        System.out.println("Fixed version:");

        SrmStudent s1 =
                new SrmStudent("Ravi", 82);

        SrmStudent s2 =
                new SrmStudent("Meera", 90);

        s1.printIdCard();
        s2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}