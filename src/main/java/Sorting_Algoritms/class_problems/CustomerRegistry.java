package main.java.Sorting_Algoritms.class_problems;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Customer {
    private final int id;
    private final String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer other = (Customer) obj;

        return id == other.id && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class CustomerRegistry {
    private final Set<Customer> customers = new HashSet<>();

    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }

    public boolean containsCustomer(Customer customer) {
        return customers.contains(customer);
    }

    public int getUniqueCount() {
        return customers.size();
    }

    public static void main(String[] args) {
        CustomerRegistry registry = new CustomerRegistry();

        Customer ashaBranchA = new Customer(101, "Asha");
        Customer ashaBranchB = new Customer(101, "Asha");
        Customer ravi = new Customer(102, "Ravi");

        System.out.println(registry.addCustomer(ashaBranchA));
        System.out.println(registry.addCustomer(ashaBranchB)
                ? "true" : "false (duplicate rejected)");
        System.out.println(registry.addCustomer(ravi));
        System.out.println("unique count " + registry.getUniqueCount());

        System.out.println("contains: "
                + registry.containsCustomer(new Customer(101, "Asha")));
    }
}
