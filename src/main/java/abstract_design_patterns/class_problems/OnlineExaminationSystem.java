package main.java.abstract_design_patterns.class_problems;

import java.util.*;

abstract class Question {
    protected int questionNumber;
    protected String questionText;

    public Question(int questionNumber, String questionText) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
    }

    public abstract boolean isCorrect(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(int number, String text, String correctAnswer) {
        super(number, text);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(int number, String text, boolean correctAnswer) {
        super(number, text);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public void answer(Question question, String answer) {
        if (submitted) {
            System.out.println("Cannot change answer after submission.");
            return;
        }

        answers.put(question, answer);
        System.out.println("Question " + question.questionNumber
                + " answered with '" + answer + "'.");
    }

    public void submit() {
        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }

        submitted = true;
        System.out.println("Examination '" + examination.getTitle()
                + "' submitted successfully.");

        int correct = 0;

        for (Map.Entry<Question, String> entry : answers.entrySet()) {
            if (entry.getKey().isCorrect(entry.getValue())) {
                correct++;
            }
        }

        System.out.println("Result for '" + examination.getTitle()
                + "' attempt: " + correct + "/"
                + examination.getQuestions().size() + " correct");
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();
    private Set<Student> submittedStudents = new HashSet<>();

    public Examination(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Attempt start(Student student) {
        if (submittedStudents.contains(student)) {
            System.out.println("Student already has a submitted attempt.");
            return null;
        }

        System.out.println("Examination '" + title
                + "' started by " + student.getName() + ".");

        return new Attempt(student, this);
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student = new Student("Swetha");

        Examination exam = new Examination("Math Quiz");

        Question q1 = new MultipleChoiceQuestion(
                1,
                "What is 2 + 2?",
                "A"
        );

        Question q2 = new MultipleChoiceQuestion(
                2,
                "What is 3 + 3?",
                "B"
        );

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt = exam.start(student);

        attempt.answer(q1, "A");
        attempt.answer(q2, "C");

        attempt.submit();

        attempt.answer(q1, "B");
    }
}