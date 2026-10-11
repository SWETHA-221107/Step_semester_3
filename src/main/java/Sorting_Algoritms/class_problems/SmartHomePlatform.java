package main.java.Sorting_Algoritms.class_problems;
import java.util.HashMap;
import java.util.Map;

interface Dimmable {
    void dim(int percentage);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void showEnergy();
}

abstract class SmartDevice {
    protected String id;
    protected boolean on;

    public SmartDevice(String id) {
        this.id = id;
        this.on = false;
    }

    public String getId() {
        return id;
    }

    public void turnOn() {
        on = true;
        System.out.println(id + " is ON");
    }

    public void turnOff() {
        on = false;
        System.out.println(id + " is OFF");
    }
}

class Light extends SmartDevice implements Dimmable, Schedulable {
    public Light(String id) {
        super(id);
    }

    @Override
    public void dim(int percentage) {
        if (percentage < 0 || percentage > 100) {
            System.out.println(id + " rejected: invalid dim level");
            return;
        }
        System.out.println(id + " dimmed to " + percentage);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends SmartDevice implements Schedulable {
    public Fan(String id) {
        super(id);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends SmartDevice implements EnergyMonitor {
    private double energy;

    public Plug(String id, double energy) {
        super(id);
        this.energy = energy;
    }

    @Override
    public void showEnergy() {
        System.out.println(id + " energy " + energy + " kWh");
    }
}

public class SmartHomePlatform {
    private final Map<String, SmartDevice> devices = new HashMap<>();

    public void addDevice(SmartDevice device) {
        devices.put(device.getId(), device);
    }

    public void processCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        String action = parts[0].toUpperCase();

        if (parts.length < 2) {
            System.out.println("Invalid command");
            return;
        }

        SmartDevice device = devices.get(parts[1]);

        if (device == null) {
            System.out.println("Device not found: " + parts[1]);
            return;
        }

        switch (action) {
            case "ON":
                device.turnOn();
                break;

            case "OFF":
                device.turnOff();
                break;

            case "DIM":
                if (device instanceof Dimmable && parts.length == 3) {
                    ((Dimmable) device).dim(
                            Integer.parseInt(parts[2]));
                } else {
                    System.out.println(device.getId()
                            + " rejected: DIM unsupported");
                }
                break;

            case "SCHEDULE":
                if (device instanceof Schedulable && parts.length == 3) {
                    ((Schedulable) device).schedule(parts[2]);
                } else {
                    System.out.println(device.getId()
                            + " rejected: SCHEDULE unsupported");
                }
                break;

            case "ENERGY":
                if (device instanceof EnergyMonitor) {
                    ((EnergyMonitor) device).showEnergy();
                } else {
                    System.out.println(device.getId()
                            + " rejected: ENERGY unsupported");
                }
                break;

            default:
                System.out.println("Unknown command: " + action);
        }
    }

    public static void main(String[] args) {
        SmartHomePlatform home = new SmartHomePlatform();

        home.addDevice(new Light("L1"));
        home.addDevice(new Fan("F1"));
        home.addDevice(new Plug("P1", 12));

        home.processCommand("ON L1");
        home.processCommand("DIM L1 40");
        home.processCommand("DIM F1 30");
        home.processCommand("SCHEDULE F1 22:00");
        home.processCommand("ENERGY P1");
        home.processCommand("ENERGY L1");
    }
}
