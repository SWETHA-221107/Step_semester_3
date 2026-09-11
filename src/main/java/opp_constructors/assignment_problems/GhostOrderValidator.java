package main.java.opp_constructors.assignment_problems;
import java.util.HashSet;

class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    // Parameterized constructor only.
    // There is no usable no-argument constructor.
    public FoodOrder(String studentName, String dishName) {

        if (studentName == null ||
            studentName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Student name cannot be blank."
            );
        }

        if (dishName == null ||
            dishName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Dish name cannot be blank."
            );
        }

        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    public void markDelivered() {

        if (!delivered) {

            delivered = true;

            System.out.println(
                    studentName
                    + "'s order marked as delivered."
            );

        } else {

            System.out.println(
                    "Warning: "
                    + studentName
                    + "'s order was already delivered."
            );
        }
    }

    public String getOrderKey() {

        return studentName.toLowerCase()
                + "|"
                + dishName.toLowerCase();
    }


    public static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        HashSet<String> acceptedOrders =
                new HashSet<>();

        for (String[] order : rawOrders) {

            try {

                if (order == null ||
                    order.length < 2) {

                    rejected++;
                    continue;
                }

                FoodOrder foodOrder =
                        new FoodOrder(
                                order[0],
                                order[1]
                        );

                acceptedOrders.add(
                        foodOrder.getOrderKey()
                );

                valid++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        System.out.println(
                "Valid: " + valid
                + " | Rejected: " + rejected
        );
    }
}


public class GhostOrderValidator {

    public static void main(String[] args) {

        String[][] rawOrders = {

                {"Ravi", "Paneer Butter Masala"},

                {"", "Chole Bhature"},

                {"Meera", " "},

                {"Divya", "Veg Biryani"}
        };


        FoodOrder.processBatch(rawOrders);


        System.out.println("\nDelivery test:");

        FoodOrder order =
                new FoodOrder(
                        "Ravi",
                        "Paneer Butter Masala"
                );

        order.markDelivered();

        // Calling it a second time gives a
        // different warning message.
        order.markDelivered();
    }
}