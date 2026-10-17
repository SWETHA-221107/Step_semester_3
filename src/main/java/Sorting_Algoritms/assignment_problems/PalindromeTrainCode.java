package main.java.Sorting_Algoritms.assignment_problems;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class PalindromeTrainCode {

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle using slow and fast pointers
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        Node secondHalf = reverse(slow.next);

        // Keep a reference to the reversed half
        Node secondHalfStart = secondHalf;

        // Step 3: Compare the first and second halves
        boolean result = true;
        Node first = head;

        while (secondHalf != null) {
            if (first.data != secondHalf.data) {
                result = false;
                break;
            }

            first = first.next;
            secondHalf = secondHalf.next;
        }

        // Step 4: Restore the original list
        slow.next = reverse(secondHalfStart);

        return result;
    }

    // Reverses a linked list and returns its new head
    private static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
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
        // Sample 1: 1 -> 2 -> 3 -> 2 -> 1
        Node a = new Node(1);
        a.next = new Node(2);
        a.next.next = new Node(3);
        a.next.next.next = new Node(2);
        a.next.next.next.next = new Node(1);

        System.out.println(isPalindrome(a));
        printList(a);

        // Sample 2: 1 -> 2 -> 3 -> 2
        Node b = new Node(1);
        b.next = new Node(2);
        b.next.next = new Node(3);
        b.next.next.next = new Node(2);

        System.out.println(isPalindrome(b));
        printList(b);

        // Sample 3: 1 -> 2 -> 2 -> 1
        Node c = new Node(1);
        c.next = new Node(2);
        c.next.next = new Node(2);
        c.next.next.next = new Node(1);

        System.out.println(isPalindrome(c));
        printList(c);
    }
}
