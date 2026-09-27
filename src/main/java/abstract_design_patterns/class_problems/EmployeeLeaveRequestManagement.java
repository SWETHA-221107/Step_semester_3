package main.java.abstract_design_patterns.class_problems;

import java.time.LocalDate;

abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract boolean isLeaveAllowed(
            LocalDate start,
            LocalDate end);

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(
            LocalDate start,
            LocalDate end) {
        return true;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(
            LocalDate start,
            LocalDate end) {

        long days =
                java.time.temporal.ChronoUnit.DAYS
                        .between(start, end) + 1;

        return days <= 5;
    }
}

class ContractEmployee extends Employee {

    public ContractEmployee(String name) {
        super(name);
    }

    @Override
    public boolean isLeaveAllowed(
            LocalDate start,
            LocalDate end) {

        long days =
                java.time.temporal.ChronoUnit.DAYS
                        .between(start, end) + 1;

        return days <= 3;
    }
}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

class LeaveRequest {

    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;

    public LeaveRequest(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public void approve() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                    "Cannot approve: request is already "
                            + status
            );
            return;
        }

        status = LeaveStatus.APPROVED;

        System.out.println(
                "Leave request for "
                        + employee.getName()
                        + " approved. Status: Approved."
        );
    }

    public void reject() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                    "Cannot reject: request is already "
                            + status
            );
            return;
        }

        status = LeaveStatus.REJECTED;

        System.out.println(
                "Leave request for "
                        + employee.getName()
                        + " rejected. Status: Rejected."
        );
    }

    public void changeToPending() {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                    "Cannot change status: "
                            + status
                            + " request cannot revert to Pending."
            );

            return;
        }

        status = LeaveStatus.PENDING;
    }

    public LeaveStatus getStatus() {
        return status;
    }
}

class LeaveManager {

    public LeaveRequest submitRequest(
            Employee employee,
            LocalDate start,
            LocalDate end) {

        if (!employee.isLeaveAllowed(start, end)) {

            System.out.println(
                    "Leave request rejected by employee policy."
            );

            return null;
        }

        LeaveRequest request =
                new LeaveRequest(
                        employee,
                        start,
                        end
                );

        System.out.println(
                "Leave request submitted by "
                        + employee.getName()
                        + " for "
                        + start
                        + " to "
                        + end
                        + ". Status: Pending."
        );

        return request;
    }
}

public class EmployeeLeaveRequestManagement {

    public static void main(String[] args) {

        LeaveManager manager =
                new LeaveManager();

        Employee john =
                new FullTimeEmployee("John Doe");

        LeaveRequest johnRequest =
                manager.submitRequest(
                        john,
                        LocalDate.of(2024, 10, 10),
                        LocalDate.of(2024, 10, 12)
                );

        johnRequest.approve();

        Employee jane =
                new PartTimeEmployee("Jane Smith");

        LeaveRequest janeRequest =
                manager.submitRequest(
                        jane,
                        LocalDate.of(2024, 11, 1),
                        LocalDate.of(2024, 11, 5)
                );

        johnRequest.changeToPending();
    }
}