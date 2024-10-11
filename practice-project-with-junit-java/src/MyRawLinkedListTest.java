import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MyRawLinkedListTest {

    private MyRawLinkedList.Node createNode(String value, MyRawLinkedList.Node next) {
        return new MyRawLinkedList.Node(value, next);
    }

    @Test
    void testRawContainsSubsequence() {
        // Create a linked list: A -> B -> C
        MyRawLinkedList.Node list1 = createNode("A", createNode("B", createNode("C", null)));

        // Test if sublist B -> C is contained in list1
        MyRawLinkedList.Node sublist1 = createNode("B", createNode("C", null));
        assertTrue(MyRawLinkedList.containsSubsequence(list1, sublist1));

        // Test if sublist A -> C is not contained in list1
        MyRawLinkedList.Node sublist2 = createNode("A", createNode("C", null));
        assertFalse(MyRawLinkedList.containsSubsequence(list1, sublist2));

        // Test if sublist A is contained in list1
        MyRawLinkedList.Node sublist3 = createNode("A", null);
        assertTrue(MyRawLinkedList.containsSubsequence(list1, sublist3));

        // Test if sublist D is not contained in list1
        MyRawLinkedList.Node sublist4 = createNode("D", null);
        assertFalse(MyRawLinkedList.containsSubsequence(list1, sublist4));

        // Test if null sublist is considered contained in list1
        assertTrue(MyRawLinkedList.containsSubsequence(list1, null));
    }

    @Test
    void testRawRemoveMaximumValues() {
        // Create a linked list: A -> B -> C and remove 1 maximum value
        MyRawLinkedList.Node list1 = createNode("A", createNode("B", createNode("C", null)));
        list1 = MyRawLinkedList.removeMaximumValues(list1, 1);
        assertEquals("[ \"A\", \"B\" ]", MyRawLinkedList.listToString(list1));

        // Create a linked list: A -> B -> C and remove 2 maximum values
        MyRawLinkedList.Node list2 = createNode("A", createNode("B", createNode("C", null)));
        list2 = MyRawLinkedList.removeMaximumValues(list2, 2);
        assertEquals("[ \"A\" ]", MyRawLinkedList.listToString(list2));

        // Create a linked list: A -> B -> C and remove 3 maximum values
        MyRawLinkedList.Node list3 = createNode("A", createNode("B", createNode("C", null)));
        list3 = MyRawLinkedList.removeMaximumValues(list3, 3);
        assertEquals("[ ]", MyRawLinkedList.listToString(list3));

        // Create a linked list: A -> B -> C and remove 0 maximum values
        MyRawLinkedList.Node list4 = createNode("A", createNode("B", createNode("C", null)));
        list4 = MyRawLinkedList.removeMaximumValues(list4, 0);
        assertEquals("[ \"A\", \"B\", \"C\" ]", MyRawLinkedList.listToString(list4));

        // Create a linked list: A -> B -> C and remove -1 maximum values (invalid case)
        MyRawLinkedList.Node list5 = createNode("A", createNode("B", createNode("C", null)));
        list5 = MyRawLinkedList.removeMaximumValues(list5, -1);
        assertEquals("[ \"A\", \"B\", \"C\" ]", MyRawLinkedList.listToString(list5));
    }

    @Test
    void testRawReverse() {
        // Create a linked list: A -> B -> C and reverse it
        MyRawLinkedList.Node list1 = createNode("A", createNode("B", createNode("C", null)));
        list1 = MyRawLinkedList.reverse(list1);
        assertEquals("[ \"C\", \"B\", \"A\" ]", MyRawLinkedList.listToString(list1));

        // Create a single node list: A and reverse it
        MyRawLinkedList.Node list2 = createNode("A", null);
        list2 = MyRawLinkedList.reverse(list2);
        assertEquals("[ \"A\" ]", MyRawLinkedList.listToString(list2));

        // Create an empty list and reverse it
        MyRawLinkedList.Node list3 = null;
        list3 = MyRawLinkedList.reverse(list3);
        assertEquals("[ ]", MyRawLinkedList.listToString(list3));

        // Create a linked list: A -> B and reverse it
        MyRawLinkedList.Node list4 = createNode("A", createNode("B", null));
        list4 = MyRawLinkedList.reverse(list4);
        assertEquals("[ \"B\", \"A\" ]", MyRawLinkedList.listToString(list4));

        // Create a linked list: A -> B -> C -> D and reverse it
        MyRawLinkedList.Node list5 = createNode("A", createNode("B", createNode("C", createNode("D", null))));
        list5 = MyRawLinkedList.reverse(list5);
        assertEquals("[ \"D\", \"C\", \"B\", \"A\" ]", MyRawLinkedList.listToString(list5));
    }
}