public class MyRawLinkedList {
    private static final long serialVersionUID = 1561306366555780559L;

    static class Node {
        private static final long serialVersionUID = -3505677833599614054L;
        String value;
        Node next = null;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        Node(String value) {
            this(value, null);
        }
    }

    /*
     * This is intentionally left private so that you can't erroneously try to
     * instantiate a `new MyRawLinkedList()`
     */
    private MyRawLinkedList() {
    }

    /*
     * These methods included as examples for how to use Node as a linked list.
     */
    public static String listToString(Node head) {
        String ret = "";
        while (head != null) {
            ret += "\"" + head.value + (head.next == null ? "\" " : "\", ");
            head = head.next;
        }
        return "[ " + ret + "]";
    }

    public static void print(Node head) {
        System.out.println(listToString(head));
    }

    public static void main(String[] args) {
        Node list1 = new Node("One", new Node("Two", new Node("Three", null)));
        print(list1);

        Node args_as_list = null;
        for (int i = args.length - 1; i >= 0; i--)
            args_as_list = new Node(args[i], args_as_list);

        print(args_as_list);

        Node list2 = null;
        list2 = new Node("a", list2);
        list2 = new Node("b", list2);
        list2 = new Node("c", list2);
        print(list2);
    }

    /*
     * Implement the methods below. Please do not change their signatures!
     */

    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static Node removeMaximumValues(Node head, int N) {
        if (N <= 0 || head == null)
            return head;

        String[] largestValues = new String[N];
        Node current = head;

        // Find the N largest unique values
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

        current = head;
        Node dummy = new Node(null, head);
        Node prev = dummy;

        // Remove nodes with these values
        while (current != null) {
            boolean toRemove = false;
            for (int i = 0; i < N; i++) {
                if (largestValues[i] != null && largestValues[i].equals(current.value)) {
                    toRemove = true;
                    break;
                }
            }
            if (toRemove) {
                prev.next = current.next;
            } else {
                prev = current;
            }
            current = current.next;
        }

        return dummy.next;
    }

    public static boolean containsSubsequence(Node head, Node other) {
        if (other == null)
            return true;
        if (head == null)
            return false;

        Node current = head;
        while (current != null) {
            if (isSubsequenceFromNode(current, other)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private static boolean isSubsequenceFromNode(Node start, Node sub) {
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

    private static boolean equals(String a, String b) {
        if (a == b)
            return true;
        if (a == null || b == null)
            return false;
        return a.equals(b);
    }
}