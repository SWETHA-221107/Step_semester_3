package main.java.access_modifiers_encapsulation.class_problems;
class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {

        if (patientId == null) {
            patientId = id;
        }
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {

        if (pin != null && pin.matches("\\d{4,6}")) {
            lockerPin = pin;
        }
    }

    public static void main(String[] args) {

        PatientProfile p = new PatientProfile();

        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000");

        System.out.println("Patient ID: " + p.getPatientId());

        PatientProfile p2 =
                new PatientProfile("Arjun Iyer");

        System.out.println("Name-only ID: "
                + p2.getPatientId());
    }
}