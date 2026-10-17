package main.java.Sorting_Algoritms.assignment_problems;

class Node {
    int coefficient;
    int exponent;
    Node next;

    Node(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}

public class PolynomialCalculator {

    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exponent > q.exponent) {
                tail.next = new Node(p.coefficient, p.exponent);
                p = p.next;
                tail = tail.next;
            } else if (q.exponent > p.exponent) {
                tail.next = new Node(q.coefficient, q.exponent);
                q = q.next;
                tail = tail.next;
            } else {
                // Same exponent: add coefficients
                int sum = p.coefficient + q.coefficient;

                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                    tail = tail.next;
                }

                p = p.next;
                q = q.next;
            }
        }

        // Copy remaining terms from p
        while (p != null) {
            tail.next = new Node(p.coefficient, p.exponent);
            tail = tail.next;
            p = p.next;
        }

        // Copy remaining terms from q
        while (q != null) {
            tail.next = new Node(q.coefficient, q.exponent);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    public static void printPolynomial(Node head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        Node current = head;
        boolean first = true;

        while (current != null) {
            int coefficient = current.coefficient;
            int exponent = current.exponent;

            if (!first) {
                System.out.print(coefficient > 0 ? " + " : " - ");
            } else if (coefficient < 0) {
                System.out.print("-");
            }

            int absoluteCoefficient = Math.abs(coefficient);

            if (exponent == 0 || absoluteCoefficient != 1) {
                System.out.print(absoluteCoefficient);
            }

            if (exponent > 0) {
                System.out.print("x");

                if (exponent > 1) {
                    System.out.print("^" + exponent);
                }
            }

            first = false;
            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1:
        // p = 5x^3 + 4x^2 + 2
        Node p = new Node(5, 3);
        p.next = new Node(4, 2);
        p.next.next = new Node(2, 0);

        // q = 5x^2 - 3x + 1
        Node q = new Node(5, 2);
        q.next = new Node(-3, 1);
        q.next.next = new Node(1, 0);

        Node result1 = addPolynomials(p, q);
        printPolynomial(result1);

        // Sample 2:
        // p = 3x^2 + 1
        Node a = new Node(3, 2);
        a.next = new Node(1, 0);

        // q = -3x^2 + 4x
        Node b = new Node(-3, 2);
        b.next = new Node(4, 1);

        Node result2 = addPolynomials(a, b);
        printPolynomial(result2);
    }
}
