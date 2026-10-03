package main.java.abstract_design_patterns.assignment_problems;

import java.util.*;

interface PricingPlan {
    double calculatePrice(double originalPrice);
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price;
    }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}

class Transaction {
    private final String description;
    private final double amount;

    Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() { return description; }
    public double getAmount() { return amount; }
}

class Purchase {
    private final String itemName;
    private final double chargedAmount;
    private boolean refunded = false;

    Purchase(String itemName, double chargedAmount) {
        this.itemName = itemName;
        this.chargedAmount = chargedAmount;
    }

    public String getItemName() { return itemName; }
    public double getChargedAmount() { return chargedAmount; }
    public boolean isRefunded() { return refunded; }
    public void markRefunded() { refunded = true; }
}

class SmartCard {
    private final String cardId;
    private final PricingPlan plan;
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Purchase> purchases = new ArrayList<>();
    private boolean blocked = false;

    SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    // Balance is derived from the transaction ledger.
    public double getBalance() {
        double balance = 0;

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;
    }

    public void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println("Top-up rejected: Minimum top-up is ₹100.00.");
            return;
        }

        if (getBalance() + amount > 5000) {
            System.out.println("Top-up rejected: Maximum balance is ₹5000.00.");
            return;
        }

        transactions.add(new Transaction("Top-up", amount));

        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                cardId, amount, getBalance());
    }

    public Purchase purchase(String itemName, double originalPrice) {
        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return null;
        }

        if (originalPrice <= 0) {
            System.out.println("Purchase failed: Price must be positive.");
            return null;
        }

        double charged = plan.calculatePrice(originalPrice);

        if (charged > getBalance()) {
            System.out.printf(
                    "Purchase failed: Insufficient balance "
                    + "(required ₹%.2f, available ₹%.2f).%n",
                    charged, getBalance());
            return null;
        }

        transactions.add(new Transaction(itemName, -charged));

        Purchase purchase = new Purchase(itemName, charged);
        purchases.add(purchase);

        System.out.printf("%s purchased for ₹%.2f.%n",
                itemName, charged);
        System.out.printf("Balance: ₹%.2f.%n", getBalance());

        return purchase;
    }

    public void refund(Purchase purchase) {
        if (blocked) {
            System.out.println("Refund rejected: Card is blocked.");
            return;
        }

        if (purchase == null || !purchases.contains(purchase)) {
            System.out.println("Refund rejected: Invalid purchase.");
            return;
        }

        if (purchase.isRefunded()) {
            System.out.println("Refund rejected: "
                    + purchase.getItemName()
                    + " has already been refunded.");
            return;
        }

        transactions.add(new Transaction(
                "Refund: " + purchase.getItemName(),
                purchase.getChargedAmount()));

        purchase.markRefunded();

        System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                purchase.getChargedAmount(),
                purchase.getItemName(),
                getBalance());
    }

    public void block() {
        blocked = true;
        System.out.println(cardId + " is blocked.");
    }

    public void unblock() {
        blocked = false;
        System.out.println(cardId + " is unblocked.");
    }

    public void miniStatement() {
        System.out.print("Mini-statement for " + cardId + ": ");

        for (int i = 0; i < transactions.size(); i++) {
            double amount = transactions.get(i).getAmount();

            System.out.printf("%s%.2f",
                    amount >= 0 ? "+" : "",
                    amount);

            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(" = ₹%.2f.%n", getBalance());
    }
}

public class CampusCanteenSmartCard {
    public static void main(String[] args) {
        SmartCard card = new SmartCard(
                "C-2045", new HostellerPlan());

        card.topUp(500);

        Purchase thali = card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Large Meal", 400);

        card.refund(thali);
        card.refund(thali);

        card.miniStatement();
    }
}