package main.java.string.class_problems;
import java.util.Random;

public class BMICalculator {

    // Method to classify BMI
    static String getBmiStatus(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } 
        else if (bmi < 25) {
            return "Normal";
        } 
        else if (bmi < 30) {
            return "Overweight";
        } 
        else {
            return "Obese";
        }
    }

    // Method to print wellness report
    static void printWellnessReport(double[] heights, double[] weights) {

        System.out.println("\n------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-10s %-15s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {

            double bmi = weights[i] / (heights[i] * heights[i]);

            System.out.printf("%-10d %-15.2f %-15.2f %-10.2f %-15s%n",
                    i + 1, heights[i], weights[i], bmi, getBmiStatus(bmi));
        }

        System.out.println("------------------------------------------------------------");
    }

    public static void main(String[] args) {

        int n = 10;

        double[] heights = new double[n];
        double[] weights = new double[n];

        Random random = new Random();

        // Generate random height and weight
        for (int i = 0; i < n; i++) {
            heights[i] = 1.50 + (1.90 - 1.50) * random.nextDouble();
            weights[i] = 45 + (100 - 45) * random.nextDouble();
        }

        printWellnessReport(heights, weights);
    }
}
