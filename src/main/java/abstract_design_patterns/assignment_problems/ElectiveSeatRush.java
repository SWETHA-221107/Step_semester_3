package main.java.abstract_design_patterns.assignment_problems;


import java.util.*;

interface CreditPolicy {
    int getLimit();
}

class RegularPolicy implements CreditPolicy {
    public int getLimit() { return 24; }
}

class HonorsPolicy implements CreditPolicy {
    public int getLimit() { return 28; }
}

class ExchangePolicy implements CreditPolicy {
    public int getLimit() { return 20; }
}

class Student {
    private final String name;
    private final CreditPolicy policy;
    private int credits;

    Student(String name, int credits, CreditPolicy policy) {
        this.name = name;
        this.credits = credits;
        this.policy = policy;
    }

    public String getName() { return name; }
    public int getCredits() { return credits; }
    public int getLimit() { return policy.getLimit(); }

    public boolean canAddCredits(int amount) {
        return credits + amount <= policy.getLimit();
    }

    public void addCredits(int amount) {
        credits += amount;
    }

    public void removeCredits(int amount) {
        credits -= amount;
    }
}

class Elective {
    private final String name;
    private final int credits;
    private final int capacity;
    private final List<Student> enrolled = new ArrayList<>();
    private final Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        if (credits <= 0 || capacity <= 0) {
            throw new IllegalArgumentException(
                    "Credits and capacity must be positive.");
        }
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getName() { return name; }
    public int getCredits() { return credits; }

    public boolean contains(Student student) {
        return enrolled.contains(student) || waitlist.contains(student);
    }

    public boolean isFull() {
        return enrolled.size() >= capacity;
    }

    public boolean addEnrolled(Student student) {
        if (isFull()) return false;
        enrolled.add(student);
        student.addCredits(credits);
        return true;
    }

    public void addToWaitlist(Student student) {
        waitlist.offer(student);
        System.out.println(student.getName()
                + " added to waitlist (position "
                + waitlist.size() + ").");
    }

    public boolean drop(Student student) {
        if (!enrolled.remove(student)) return false;

        student.removeCredits(credits);
        System.out.println(student.getName() + " dropped "
                + name + " (credits: " + student.getCredits()
                + "/" + student.getLimit() + ").");

        promoteNextEligible();
        return true;
    }

    private void promoteNextEligible() {
        if (isFull()) return;

        Iterator<Student> iterator = waitlist.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();

            if (student.canAddCredits(credits)) {
                iterator.remove();
                addEnrolled(student);

                System.out.println(student.getName()
                        + " promoted from waitlist and enrolled in "
                        + name + " (credits: " + student.getCredits()
                        + "/" + student.getLimit() + ").");
                return;
            }
        }
    }

    public void enroll(Student student) {
        if (contains(student)) {
            System.out.println("Enrollment failed: "
                    + student.getName()
                    + " is already enrolled or waitlisted.");
            return;
        }

        // Credit limit is checked before seat availability.
        if (!student.canAddCredits(credits)) {
            System.out.println("Enrollment failed: "
                    + student.getName()
                    + " would exceed the "
                    + student.getLimit()
                    + " credit limit ("
                    + (student.getCredits() + credits)
                    + "/" + student.getLimit() + ").");
            return;
        }

        if (isFull()) {
            System.out.println(name + " is full.");
            addToWaitlist(student);
            return;
        }

        addEnrolled(student);

        System.out.println(student.getName() + " enrolled in "
                + name + " (credits: " + student.getCredits()
                + "/" + student.getLimit() + ").");
    }
}

class EnrollmentService {
    public void enroll(Student student, Elective elective) {
        elective.enroll(student);
    }

    public void drop(Student student, Elective elective) {
        if (!elective.drop(student)) {
            System.out.println(student.getName()
                    + " is not enrolled in " + elective.getName() + ".");
        }
    }
}

public class ElectiveSeatRush {
    public static void main(String[] args) {
        EnrollmentService service = new EnrollmentService();

        Elective cloud = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", 20, new RegularPolicy());
        Student ravi = new Student("Ravi", 22, new HonorsPolicy());
        Student neha = new Student("Neha", 12, new ExchangePolicy());
        Student kiran = new Student("Kiran", 22, new RegularPolicy());

        service.enroll(asha, cloud);
        service.enroll(ravi, cloud);
        service.enroll(neha, cloud);
        service.enroll(kiran, cloud);

        service.drop(asha, cloud);
    }
}
