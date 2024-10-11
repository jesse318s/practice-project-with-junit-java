import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {

    MyLinkedList list;
    MyLinkedList sublist;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList();
        sublist = new MyLinkedList();
    }

    @Test
    void testContainsSubsequence() {
        // Both lists empty
        assertTrue(list.containsSubsequence(sublist));

        // Main list empty, subsequence non-empty
        sublist.addLast("A");
        assertFalse(list.containsSubsequence(sublist));

        // Subsequence empty, main list non-empty
        list.addLast("A");
        assertTrue(list.containsSubsequence(sublist));

        // Subsequence is a valid subsequence
        list.addLast("B");
        list.addLast("C");
        sublist = new MyLinkedList();
        sublist.addLast("A");
        sublist.addLast("B");
        assertTrue(list.containsSubsequence(sublist));

        // Subsequence is not a valid subsequence
        sublist = new MyLinkedList();
        sublist.addLast("A");
        sublist.addLast("D");
        assertFalse(list.containsSubsequence(sublist));
    }

    @Test
    void testRemoveMaximumValues() {
        // N is zero
        list.addLast("A");
        list.addLast("B");
        list.removeMaximumValues(0);
        assertEquals(2, list.size);

        // N is negative
        list.removeMaximumValues(-1);
        assertEquals(2, list.size);

        // N greater than number of unique elements
        list.removeMaximumValues(3);
        assertEquals(0, list.size);

        // N equal to number of unique elements
        list.addLast("A");
        list.addLast("B");
        list.removeMaximumValues(2);
        assertEquals(0, list.size);

        // N less than number of unique elements
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.removeMaximumValues(1);
        assertEquals(2, list.size);
        assertFalse(list.containsSubsequence(new MyLinkedList() {
            {
                addLast("C");
            }
        }));
    }

    @Test
    void testReverse() {
        // Reversing an empty list
        list.reverse();
        assertNull(list.head);

        // Reversing a list with one element
        list.addLast("A");
        list.reverse();
        assertEquals("A", list.head.value);
        assertEquals("A", list.tail.value);

        // Reversing a list with two elements
        list.addLast("B");
        list.reverse();
        assertEquals("B", list.head.value);
        assertEquals("A", list.tail.value);

        // Reversing a list with multiple elements
        list.addLast("C");
        list.addLast("D");
        list.reverse();
        assertEquals("D", list.head.value);
        assertEquals("B", list.tail.value);

        // Reversing a list with null values
        list = new MyLinkedList();
        list.addLast(null);
        list.addLast("A");
        list.reverse();
        assertEquals("A", list.head.value);
        assertNull(list.tail.value);
    }
}