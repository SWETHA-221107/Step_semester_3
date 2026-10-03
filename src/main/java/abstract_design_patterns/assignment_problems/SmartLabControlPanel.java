package main.java.abstract_design_patterns.assignment_problems;

import java.util.*;

interface Capability {
    String name();
    boolean apply(String value, String deviceName);
}

class PowerCapability implements Capability {
    private boolean on;

    public String name() { return "power"; }

    public boolean apply(String value, String device) {
        if (!value.equalsIgnoreCase("ON")
                && !value.equalsIgnoreCase("OFF")) {
            System.out.println("Rejected: Power must be ON or OFF.");
            return false;
        }

        on = value.equalsIgnoreCase("ON");
        System.out.println(device + ": " + (on ? "ON" : "OFF") + ".");
        return true;
    }
}

class BrightnessCapability implements Capability {
    private int brightness;

    public String name() { return "brightness"; }

    public boolean apply(String value, String device) {
        try {
            int level = Integer.parseInt(value);

            if (level < 0 || level > 100) {
                System.out.println("Rejected: Brightness must be between 0 and 100%.");
                return false;
            }

            brightness = level;
            System.out.println(device + ": brightness set to "
                    + brightness + "%.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Rejected: Invalid brightness value.");
            return false;
        }
    }
}

class TemperatureCapability implements Capability {
    private int temperature;

    public String name() { return "temperature"; }

    public boolean apply(String value, String device) {
        try {
            int temp = Integer.parseInt(value);

            if (temp < 16 || temp > 30) {
                System.out.println("Rejected: " + device
                        + " temperature must be between 16°C and 30°C.");
                return false;
            }

            temperature = temp;
            System.out.println(device + ": temperature set to "
                    + temperature + "°C.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Rejected: Invalid temperature value.");
            return false;
        }
    }
}

class Device {
    private final String name;
    private final Map<String, Capability> capabilities = new LinkedHashMap<>();

    Device(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addCapability(Capability capability) {
        capabilities.put(capability.name(), capability);
        System.out.println(name + ": "
                + capability.name() + " capability added.");
    }

    public boolean apply(String capabilityName, String value) {
        Capability capability = capabilities.get(capabilityName.toLowerCase());

        if (capability == null) return false;
        return capability.apply(value, name);
    }

    public boolean supports(String capabilityName) {
        return capabilities.containsKey(capabilityName.toLowerCase());
    }
}

class SceneStep {
    String capability;
    String value;

    SceneStep(String capability, String value) {
        this.capability = capability;
        this.value = value;
    }
}

class Scene {
    private final String name;
    private final List<SceneStep> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    public void addStep(String capability, String value) {
        steps.add(new SceneStep(capability, value));
    }

    public void execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");
        int applied = 0;

        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (device.supports(step.capability)
                        && device.apply(step.capability, step.value)) {
                    applied++;
                }
            }
        }

        System.out.println("Scene '" + name
                + "' completed: " + applied + " actions applied.");
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(ac, lights, projector);

        Scene lecture = new Scene("Lecture Mode");
        lecture.addStep("power", "ON");
        lecture.addStep("brightness", "40");
        lecture.addStep("temperature", "24");
        lecture.execute(devices);

        ac.apply("temperature", "12");

        projector.addCapability(new BrightnessCapability());
        projector.apply("brightness", "70");
    }
}
