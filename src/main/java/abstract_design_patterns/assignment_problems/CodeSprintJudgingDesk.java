
package main.java.abstract_design_patterns.assignment_problems;

import java.util.*;

interface ScoringRule {
    double calculate(double idea, double execution, double presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculate(double i, double e, double p) {
        return i * 0.5 + e * 0.3 + p * 0.2;
    }
}

class OpenScoring implements ScoringRule {
    public double calculate(double i, double e, double p) {
        return (i + e + p) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Project {
    String title;
    Team team;
    Double finalScore;

    Project(String title, Team team) {
        this.title = title;
        this.team = team;
    }
}

class Team {
    String name;
    List<Student> members;
    ScoringRule scoringRule;
    Project project;

    Team(String name, List<Student> members, ScoringRule rule) {
        this.name = name;
        this.members = new ArrayList<>(members);
        this.scoringRule = rule;
    }
}

class Hackathon {
    enum State { OPEN, JUDGING, PUBLISHED }

    private State state = State.OPEN;
    private final List<Team> teams = new ArrayList<>();
    private final Set<Student> registeredStudents =
            Collections.newSetFromMap(new IdentityHashMap<>());

    public boolean register(Team team) {
        if (state != State.OPEN) {
            System.out.println("Registration is closed.");
            return false;
        }

        if (team.members.size() < 2 || team.members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return false;
        }

        for (Student student : team.members) {
            if (registeredStudents.contains(student)) {
                System.out.println("Registration failed: "
                        + student.name + " already belongs to a team.");
                return false;
            }
        }

        teams.add(team);
        registeredStudents.addAll(team.members);

        String track = team.scoringRule instanceof InnovationScoring
                ? "Innovation" : "Open";

        System.out.println("Team " + team.name + " registered ("
                + team.members.size() + " members, " + track + " track).");
        return true;
    }

    public void submitProject(Team team, String title) {
        if (state != State.OPEN || !teams.contains(team)) {
            System.out.println("Project submission failed.");
            return;
        }

        if (team.project != null) {
            System.out.println("A team can submit only one project.");
            return;
        }

        team.project = new Project(title, team);
        state = State.JUDGING;
        System.out.println("Project '" + title
                + "' submitted by " + team.name + ".");
    }

    public void score(Project project, double idea,
                      double execution, double presentation) {
        if (state == State.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }

        if (project == null || project.team.project != project
                || idea < 0 || idea > 10
                || execution < 0 || execution > 10
                || presentation < 0 || presentation > 10) {
            System.out.println("Invalid project or score.");
            return;
        }

        project.finalScore = project.team.scoringRule.calculate(
                idea, execution, presentation);

        System.out.println("Score recorded for '" + project.title + "'.");
        System.out.printf("Final score: %.2f%n", project.finalScore);
    }

    public void publishResults() {
        if (state == State.PUBLISHED) {
            System.out.println("Results already published.");
            return;
        }

        state = State.PUBLISHED;
        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        Team byteBusters = new Team("ByteBusters",
                Arrays.asList(asha, ravi, neha),
                new InnovationScoring());

        Team soloCoder = new Team("SoloCoder",
                Arrays.asList(kiran), new OpenScoring());

        hackathon.register(byteBusters);
        hackathon.register(soloCoder);

        hackathon.submitProject(byteBusters, "SmartAttend");
        hackathon.score(byteBusters.project, 8, 7, 9);
        hackathon.publishResults();
        hackathon.score(byteBusters.project, 10, 7, 9);
    }
}