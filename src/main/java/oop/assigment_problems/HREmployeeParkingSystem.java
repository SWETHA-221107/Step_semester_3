package main.java.oop.assigment_problems;
// ==========================================
// EMPLOYEE CLASS
// ==========================================

class Employee {

    private String empId;
    private String empName;
    private double salary;

    // Constructor
    Employee(
            String empId,
            String empName,
            double salary) {

        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    // Get salary
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


// ==========================================
// MANAGER EMPLOYEE
// ==========================================

class ManagerEmployee extends Employee {

    private double teamBonus;

    // Constructor
    ManagerEmployee(
            String empId,
            String empName,
            double salary,
            double teamBonus) {

        super(empId, empName, salary);

        this.teamBonus = teamBonus;
    }

    // Effective salary
    public double effectiveSalary() {

        return getSalary() + teamBonus;
    }
}


// ==========================================
// INTERN EMPLOYEE
// ==========================================

class InternEmployee extends Employee {

    private double stipendCap;

    // Constructor
    InternEmployee(
            String empId,
            String empName,
            double salary,
            double stipendCap) {

        super(empId, empName, salary);

        this.stipendCap = stipendCap;
    }

    // Effective salary
    public double effectiveSalary() {

        if (getSalary() < stipendCap) {
            return getSalary();
        } else {
            return stipendCap;
        }
    }
}


// ==========================================
// PARKING SLOT
// ==========================================

class ParkingSlot {

    private String slotNo;
    private int capacity;
    private int occupiedCount;

    // Constructor
    ParkingSlot(
            String slotNo,
            int capacity,
            int occupiedCount) {

        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // Allot vehicle
    public void allot(String vehicleNo) {

        if (occupiedCount < capacity) {

            occupiedCount++;

            System.out.println(
                    vehicleNo
                    + " allotted to slot "
                    + slotNo
            );
        }
    }

    // Check availability
    public boolean isAvailable() {

        return occupiedCount < capacity;
    }

    // Get slot number
    public String getSlotNo() {

        return slotNo;
    }
}


// ==========================================
// COMPANY EMPLOYEE RECORD
// ==========================================

class CompanyEmployeeRecord {

    private String name;
    private String empId;

    // Object as a field
    private Employee employee;

    // Another object as a field
    private ParkingSlot slot;


    // Static counter
    static int totalRecords = 0;


    // Constructor
    CompanyEmployeeRecord(
            String name,
            String empId,
            Employee employee) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;

        // Initially no parking is assigned
        this.slot = null;

        totalRecords++;
    }


    // Assign parking slot
    public void setSlot(ParkingSlot slot) {

        this.slot = slot;
    }


    // Calculate effective pay
    private double getEffectivePay() {

        if (employee instanceof ManagerEmployee) {

            ManagerEmployee manager =
                    (ManagerEmployee) employee;

            return manager.effectiveSalary();

        } else if (employee instanceof InternEmployee) {

            InternEmployee intern =
                    (InternEmployee) employee;

            return intern.effectiveSalary();

        } else {

            return employee.getSalary();
        }
    }


    // Print complete profile
    public void fullProfile() {

        System.out.print(
                name
                + " | Pay: Rs "
                + getEffectivePay()
                + " | Slot: "
        );


        // Null safety
        if (slot != null) {

            System.out.println(
                    slot.getSlotNo()
            );

        } else {

            System.out.println(
                    "no parking assigned"
            );
        }
    }
}


// ==========================================
// MAIN CLASS
// ==========================================

public class HREmployeeParkingSystem {

    // Static method to find an available slot
    public static ParkingSlot findAvailableSlot(
            ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot != null && slot.isAvailable()) {
                return slot;
            }
        }

        return null;
    }


    // Safely allot parking
    public static void safeAllot(
            ParkingSlot[] slots,
            CompanyEmployeeRecord record,
            String vehicleNo) {

        ParkingSlot availableSlot =
                findAvailableSlot(slots);

        if (availableSlot != null) {

            availableSlot.allot(vehicleNo);

            record.setSlot(availableSlot);

        } else {

            System.out.println(
                    "No parking available for "
                    + vehicleNo
            );
        }
    }


    public static void main(String[] args) {

        // ==========================================
        // CREATE EMPLOYEES
        // ==========================================

        ManagerEmployee divyaEmployee =
                new ManagerEmployee(
                        "E101",
                        "Divya",
                        70000,
                        8000
                );


        Employee karanEmployee =
                new Employee(
                        "E102",
                        "Karan",
                        40000
                );


        InternEmployee meeraEmployee =
                new InternEmployee(
                        "E103",
                        "Meera",
                        12000,
                        10000
                );


        // ==========================================
        // CREATE EMPLOYEE RECORDS
        // ==========================================

        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E101",
                        divyaEmployee
                );


        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E102",
                        karanEmployee
                );


        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E103",
                        meeraEmployee
                );


        // ==========================================
        // CREATE PARKING SLOTS
        // ==========================================

        ParkingSlot[] slots = {

            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };


        // ==========================================
        // ALLOT PARKING TO ONLY TWO EMPLOYEES
        // ==========================================

        safeAllot(
                slots,
                divya,
                "TN01AA1111"
        );


        safeAllot(
                slots,
                karan,
                "TN01AA2222"
        );


        // Meera intentionally has no parking.
        // Her slot remains null.


        // ==========================================
        // DISPLAY PROFILES
        // ==========================================

        System.out.println("\nEmployee Profiles:");

        divya.fullProfile();

        karan.fullProfile();

        meera.fullProfile();


        // ==========================================
        // DISPLAY TOTAL RECORDS
        // ==========================================

        System.out.println(
                "Total records: "
                + CompanyEmployeeRecord.totalRecords
        );
    }
}