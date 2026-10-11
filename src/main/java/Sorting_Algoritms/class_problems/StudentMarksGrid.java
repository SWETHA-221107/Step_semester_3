package main.java.Sorting_Algoritms.class_problems;

public class StudentMarksGrid {

    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};
        String[] students = {"Anu", "Ravi", "Meena"};

        // null represents a missing mark.
        Integer[][] marks = {
            {80, 90, 70},
            {60, 85, null},
            {95, 75, 88}
        };

        // Calculate total marks for each student.
        System.out.println("Student totals:");

        for (int i = 0; i < students.length; i++) {
            int total = 0;

            for (int j = 0; j < subjects.length; j++) {
                if (marks[i][j] != null) {
                    total += marks[i][j];
                }
            }

            System.out.println("Totals " + students[i] + " " + total);
        }

        // Find the topper for each subject.
        System.out.println("\nSubject toppers:");

        for (int j = 0; j < subjects.length; j++) {
            int highestMark = -1;
            String topper = "None";

            for (int i = 0; i < students.length; i++) {
                if (marks[i][j] != null
                        && marks[i][j] > highestMark) {
                    highestMark = marks[i][j];
                    topper = students[i];
                }
            }

            System.out.println("toppers " + subjects[j] + " "
                    + topper + " " + highestMark);
        }
    }
}

