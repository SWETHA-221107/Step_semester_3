package main.java.string_operations_performance.assigment_problems;
import java.util.Scanner;

public class ATMpinValidator {

    static void checkPinLength(String pin) {

        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        checkPinLength(pin);

        sc.close();
    }
}
