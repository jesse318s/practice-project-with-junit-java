public class MyLinkedList {

    private static final long serialVersionUID = 1663679278942178557L;

    static class Node {
        private static final long serialVersionUID = -539394075146871892L;
        String value;
        Node next;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        Node(String value) {
            this(value, null);
        }
    }

    protected Node head = null;
    protected Node tail = null;
    protected int size = 0;

    public void addFirst(String value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
        size++;
    }

    public void addLast(String value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void add(int index, String value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node newNode = new Node(value);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            if (current.next == null) {
                tail = newNode;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
        } else {
            return;
        }
        if (head == null) {
            tail = null;
        }
        if (size > 0)
            size--;
    }

    public void removeLast() {
        if (head == null) { // empty list
            return;
        } else if (head == tail) {
            // single element list
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            current.next = null;
        }
        size--;
    }

    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        else if (index == 0)
            removeFirst();
        else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
            }
            size--;
        }
    }

    /*
     * Implement the methods below. Please do not change their signatures!
     */

    public void reverse() {
        if (head == null)
            return;

        Node prev = null;
        Node current = head;
        Node next = null;
        tail = head;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void removeMaximumValues(int N) {
        if (N <= 0 || head == null)
            return;

        // Find the N largest unique values
        String[] largestValues = new String[N];
        Node current = head;

        while (current != null) {
            String value = current.value;
            for (int i = 0; i < N; i++) {
                if (largestValues[i] == null || (value != null && value.compareTo(largestValues[i]) > 0)) {
                    for (int j = N - 1; j > i; j--) {
                        largestValues[j] = largestValues[j - 1];
                    }
                    largestValues[i] = value;
                    break;
                }
            }
            current = current.next;
        }

        // Remove nodes with these values
        for (String value : largestValues) {
            if (value == null)
                continue;
            current = head;
            Node prev = null;
            while (current != null) {
                if (current.value != null && current.value.equals(value)) {
                    if (prev == null) {
                        head = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    if (current == tail) {
                        tail = prev;
                    }
                    size--;
                } else {
                    prev = current;
                }
                current = current.next;
            }
        }
    }

    public Boolean containsSubsequence(MyLinkedList other) {
        if (other == null)
            return null;
        if (other.head == null)
            return true;

        Node current = head;
        while (current != null) {
            if (isSubsequenceFromNode(current, other.head)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private boolean isSubsequenceFromNode(Node start, Node sub) {
        Node current = start;
        Node subCurrent = sub;
        while (subCurrent != null) {
            if (current == null || !equals(current.value, subCurrent.value)) {
                return false;
            }
            current = current.next;
            subCurrent = subCurrent.next;
        }
        return true;
    }

    private boolean equals(String a, String b) {
        if (a == b)
            return true;
        if (a == null || b == null)
            return false;
        return a.equals(b);
    }
}