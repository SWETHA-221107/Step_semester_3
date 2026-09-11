package main.java.oop.assigment_problems;
// Parent class
class Employee {

    private String empId;
    private String empName;
    private double salary;

    // Constructor
    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    // Method to get salary
    public double getSalary() {
        return salary;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }
}


// ManagerEmployee inherits Employee
class ManagerEmployee extends Employee {

    private double teamBonus;

    // Constructor
    ManagerEmployee(String empId, String empName,
                    double salary, double teamBonus) {

        super(empId, empName, salary);

        this.teamBonus = teamBonus;
    }

    // Calculate manager's effective salary
    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


// InternEmployee inherits Employee
class InternEmployee extends Employee {

    private double stipendCap;

    // Constructor
    InternEmployee(String empId, String empName,
                   double salary, double stipendCap) {

        super(empId, empName, salary);

        this.stipendCap = stipendCap;
    }

    // Return whichever is smaller
    public double effectiveSalary() {

        if (getSalary() < stipendCap) {
            return getSalary();
        } else {
            return stipendCap;
        }
    }
}


// Main class
public class EmployeeParkingSystem {

    public static void main(String[] args) {

        // Plain Employee
        Employee plainEmployee =
                new Employee(
                        "E101",
                        "Arun",
                        40000
                );

        // Manager Employee
        ManagerEmployee manager =
                new ManagerEmployee(
                        "E102",
                        "Divya",
                        70000,
                        8000
                );

        // Intern Employee
        InternEmployee intern =
                new InternEmployee(
                        "E103",
                        "Meera",
                        12000,
                        10000
                );


        // Plain employee
        if (plainEmployee instanceof Employee) {

            System.out.println(
                    "Plain employee pay: Rs "
                    + plainEmployee.getSalary()
            );
        }


        // Manager employee
        if (manager instanceof ManagerEmployee) {

            System.out.println(
                    "Manager effective pay: Rs "
                    + manager.effectiveSalary()
            );
        }


        // Intern employee
        if (intern instanceof InternEmployee) {

            System.out.println(
                    "Intern effective pay: Rs "
                    + intern.effectiveSalary()
            );
        }
    }
}