package main.java.Sorting_Algoritms.assignment_problems;

class BrowserNode {
    String url;
    BrowserNode prev;
    BrowserNode next;

    BrowserNode(String url) {
        this.url = url;
        this.prev = null;
        this.next = null;
    }
}

class BrowserHistory {
    private BrowserNode current;

    public BrowserHistory(String homepage) {
        current = new BrowserNode(homepage);
    }

    public void visit(String url) {
        BrowserNode newNode = new BrowserNode(url);

        // Discard all forward history
        current.next = null;

        // Connect the new page after the current page
        newNode.prev = current;
        current.next = newNode;

        // Move to the new page
        current = newNode;
    }

    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }

        return current.url;
    }

    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }

        return current.url;
    }
}

public class CampusPortalBrowserHistory {
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));

        browser.visit("d.com");

        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
}
