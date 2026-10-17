package main.java.Sorting_Algoritms.assignment_problems;
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MergingTwoTicketCounters {

    public static Node mergeSorted(Node a, Node b) {
        // Dummy node simplifies building the merged list
        Node dummy = new Node(0);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }

            tail = tail.next;
        }

        // Attach the remaining nodes
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }

        return dummy.next;
    }

    public static void printList(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: a = 3 -> 8 -> 15
        Node a = new Node(3);
        a.next = new Node(8);
        a.next.next = new Node(15);

        // b = 5 -> 8 -> 12 -> 20
        Node b = new Node(5);
        b.next = new Node(8);
        b.next.next = new Node(12);
        b.next.next.next = new Node(20);

        Node merged = mergeSorted(a, b);
        printList(merged);

        // Sample 2: a is empty, b = 4
        Node empty = null;
        Node single = new Node(4);

        Node merged2 = mergeSorted(empty, single);
        printList(merged2);
    }
}
