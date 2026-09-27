package main.java.abstract_design_patterns.class_problems;
import java.util.ArrayList;
import java.util.List;

class FoodItem {
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class LineItem {
    private FoodItem foodItem;
    private int quantity;

    public LineItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public double getTotal() {
        return foodItem.getPrice() * quantity;
    }

    public String getDescription() {
        return foodItem.getName()
                + " (Qty " + quantity + ")";
    }
}

interface IPaymentMethod {
    boolean pay(double amount);

    String getPaymentName();
}

class CreditCardPayment implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.printf(
                "Payment via Credit Card successful: $%.2f%n",
                amount
        );
        return true;
    }

    @Override
    public String getPaymentName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.printf(
                "Payment via Digital Wallet failed: $%.2f%n",
                amount
        );
        return false;
    }

    @Override
    public String getPaymentName() {
        return "Digital Wallet";
    }
}

class CashOnDelivery implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.printf(
                "Cash on Delivery selected: $%.2f%n",
                amount
        );
        return true;
    }

    @Override
    public String getPaymentName() {
        return "Cash on Delivery";
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void notifyCustomer(String message) {
        System.out.println(
                "Notification to "
                        + name
                        + ": "
                        + message
        );
    }
}

class Order {

    private static int nextOrderId = 123;

    private int orderId;
    private Customer customer;
    private List<LineItem> items;
    private String status;

    public Order(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "Created";
        this.orderId = nextOrderId++;

        System.out.println("Order created.");
    }

    public void addItem(
            FoodItem foodItem,
            int quantity) {

        if (quantity <= 0) {
            System.out.println(
                    "Quantity must be positive."
            );
            return;
        }

        LineItem item =
                new LineItem(
                        foodItem,
                        quantity
                );

        items.add(item);

        System.out.println(
                "Added " + item.getDescription()
        );
    }

    public double calculateTotal() {

        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void placeOrder(
            IPaymentMethod paymentMethod) {

        if (items.isEmpty()) {

            System.out.println(
                    "Cannot place order: "
                            + "Order must contain at least one item."
            );

            return;
        }

        status = "Pending Payment";

        System.out.println(
                "Order placed successfully."
        );

        boolean paymentSuccessful =
                paymentMethod.pay(
                        calculateTotal()
                );

        if (paymentSuccessful) {

            status = "Paid";

            System.out.println(
                    "Order status: Paid."
            );

            customer.notifyCustomer(
                    "Order #" + orderId
                            + " placed and paid."
            );

        } else {

            status = "Pending Payment";

            System.out.println(
                    "Order status: Pending Payment."
            );

            customer.notifyCustomer(
                    "Order #" + orderId
                            + " placed, awaiting payment."
            );
        }
    }
}

public class FoodOrderFlexiblePayment {

    public static void main(String[] args) {

        Customer customer =
                new Customer("Swetha");

        Order order1 =
                new Order(customer);

        FoodItem pizza =
                new FoodItem("Pizza", 200);

        FoodItem soda =
                new FoodItem("Soda", 50);

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        // Empty cart test
        Order emptyOrder =
                new Order(customer);

        emptyOrder.placeOrder(
                new CreditCardPayment()
        );

        // Successful payment
        order1.placeOrder(
                new CreditCardPayment()
        );

        // Failed payment
        Order order2 =
                new Order(customer);

        FoodItem burger =
                new FoodItem("Burger", 150);

        order2.addItem(burger, 1);

        order2.placeOrder(
                new DigitalWalletPayment()
        );
    }
}