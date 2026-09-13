package main.java.access_modifiers_encapsulation.class_problems;
class PatientVitals {

    private double[] readings;
    private int count;

    PatientVitals(double[] initialReadings) {

        readings = new double[500];
        count = 0;

        for (double reading : initialReadings) {
            recordReading(reading);
        }
    }

    void recordReading(double reading) {

        if (reading <= 0 || reading > 45) {
            return;
        }

        readings[count] = reading;
        count++;
    }

    double getAverage() {

        if (count == 0) {
            return 0;
        }

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }

        return sum / count;
    }

    double[] getAllReadings() {

        double[] copy = new double[count];

        for (int i = 0; i < count; i++) {
            copy[i] = readings[i];
        }

        return copy;
    }

    public static void main(String[] args) {

        PatientVitals v = new PatientVitals(
                new double[]{36.5, -2, 37.1}
        );

        double[] values = v.getAllReadings();

        for (double value : values) {
            System.out.println(value);
        }

        System.out.println("Average: " + v.getAverage());
    }
}