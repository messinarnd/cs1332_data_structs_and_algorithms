import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Simple test cases for heaps and priority queues.
 * Write your own tests to ensure you cover all edge cases.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class HeapPQStudentTests {

    private static final int TIMEOUT = 200;
    private HeapInterface<Integer> minHeap;
    private PriorityQueueInterface<Integer> minPriorityQueue;

    @Before
    public void setUp() {
        minHeap = new MinHeap<>();
        minPriorityQueue = new MinPriorityQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testHeapAdd() {
        minHeap.add(35);
        Integer[] expected = new Integer[HeapInterface.INITIAL_CAPACITY];
        expected[1] = 35;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(1, minHeap.size());

        minHeap.add(23);
        expected[1] = 23;
        expected[2] = 35;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(2, minHeap.size());

        minHeap.add(28);
        expected[1] = 23;
        expected[2] = 35;
        expected[3] = 28;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(3, minHeap.size());

        minHeap.add(12);
        expected[1] = 12;
        expected[2] = 23;
        expected[3] = 28;
        expected[4] = 35;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(4, minHeap.size());

        minHeap.add(20);
        expected[1] = 12;
        expected[2] = 20;
        expected[3] = 28;
        expected[4] = 35;
        expected[5] = 23;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(5, minHeap.size());

        minHeap.add(14);
        expected[1] = 12;
        expected[2] = 20;
        expected[3] = 14;
        expected[4] = 35;
        expected[5] = 23;
        expected[6] = 28;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(6, minHeap.size());

        minHeap.add(9);
        expected[1] = 9;
        expected[2] = 20;
        expected[3] = 12;
        expected[4] = 35;
        expected[5] = 23;
        expected[6] = 28;
        expected[7] = 14;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(7, minHeap.size());

        minHeap.add(31);
        expected[1] = 9;
        expected[2] = 20;
        expected[3] = 12;
        expected[4] = 31;
        expected[5] = 23;
        expected[6] = 28;
        expected[7] = 14;
        expected[8] = 35;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(8, minHeap.size());

        minHeap.add(24);
        expected[1] = 9;
        expected[2] = 20;
        expected[3] = 12;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 28;
        expected[7] = 14;
        expected[8] = 35;
        expected[9] = 31;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(9, minHeap.size());

        minHeap.add(54);
        expected[1] = 9;
        expected[2] = 20;
        expected[3] = 12;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 28;
        expected[7] = 14;
        expected[8] = 35;
        expected[9] = 31;
        expected[10] = 54;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(10, minHeap.size());

        minHeap.add(41);
        expected[1] = 9;
        expected[2] = 20;
        expected[3] = 12;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 28;
        expected[7] = 14;
        expected[8] = 35;
        expected[9] = 31;
        expected[10] = 54;
        expected[11] = 41;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(11, minHeap.size());


        minHeap.add(7);
        expected[1] = 7;
        expected[2] = 20;
        expected[3] = 9;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 14;
        expected[8] = 35;
        expected[9] = 31;
        expected[10] = 54;
        expected[11] = 41;
        expected[12] = 28;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(12, minHeap.size());

        minHeap.add(15);
        expected[1] = 7;
        expected[2] = 20;
        expected[3] = 9;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 14;
        expected[8] = 35;
        expected[9] = 31;
        expected[10] = 54;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(13, minHeap.size());

        minHeap.add(17);
        expected[1] = 7;
        expected[2] = 20;
        expected[3] = 9;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 14;
        expected[8] = 35;
        expected[9] = 31;
        expected[10] = 54;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(14, minHeap.size());


        minHeap.add(11);
        expected[1] = 7;
        expected[2] = 20;
        expected[3] = 9;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 35;
        expected[9] = 31;
        expected[10] = 54;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(15, minHeap.size());

        minHeap.add(26);
        expected = new Integer[32];
        expected[1] = 7;
        expected[2] = 20;
        expected[3] = 9;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 31;
        expected[10] = 54;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(16, minHeap.size());

        minHeap.add(29);
        expected[1] = 7;
        expected[2] = 20;
        expected[3] = 9;
        expected[4] = 24;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 31;
        expected[10] = 54;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(17, minHeap.size());

        minHeap.add(3);
        expected[1] = 3;
        expected[2] = 7;
        expected[3] = 9;
        expected[4] = 20;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 24;
        expected[10] = 54;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        expected[18] = 31;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(18, minHeap.size());

        minHeap.add(42);
        expected[1] = 3;
        expected[2] = 7;
        expected[3] = 9;
        expected[4] = 20;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 24;
        expected[10] = 54;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        expected[18] = 31;
        expected[19] = 42;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(19, minHeap.size());

        minHeap.add(48);
        expected[1] = 3;
        expected[2] = 7;
        expected[3] = 9;
        expected[4] = 20;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 24;
        expected[10] = 48;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        expected[18] = 31;
        expected[19] = 42;
        expected[20] = 54;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(20, minHeap.size());

        minHeap.add(69);
        expected[1] = 3;
        expected[2] = 7;
        expected[3] = 9;
        expected[4] = 20;
        expected[5] = 23;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 24;
        expected[10] = 48;
        expected[11] = 41;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        expected[18] = 31;
        expected[19] = 42;
        expected[20] = 54;
        expected[21] = 69;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(21, minHeap.size());

        minHeap.add(2);
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 9;
        expected[4] = 20;
        expected[5] = 7;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 24;
        expected[10] = 48;
        expected[11] = 23;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        expected[18] = 31;
        expected[19] = 42;
        expected[20] = 54;
        expected[21] = 69;
        expected[22] = 41;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(22, minHeap.size());

        minHeap.add(70);
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 9;
        expected[4] = 20;
        expected[5] = 7;
        expected[6] = 12;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 24;
        expected[10] = 48;
        expected[11] = 23;
        expected[12] = 28;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        expected[18] = 31;
        expected[19] = 42;
        expected[20] = 54;
        expected[21] = 69;
        expected[22] = 41;
        expected[23] = 70;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(23, minHeap.size());

        minHeap.add(1);
        expected[1] = 1;
        expected[2] = 3;
        expected[3] = 2;
        expected[4] = 20;
        expected[5] = 7;
        expected[6] = 9;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 24;
        expected[10] = 48;
        expected[11] = 23;
        expected[12] = 12;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        expected[18] = 31;
        expected[19] = 42;
        expected[20] = 54;
        expected[21] = 69;
        expected[22] = 41;
        expected[23] = 70;
        expected[24] = 28;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(24, minHeap.size());

        minHeap.add(100);
        expected[25] = 100;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(25, minHeap.size());

        minHeap.add(101);
        expected[26] = 101;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(26, minHeap.size());

        minHeap.add(102);
        expected[27] = 102;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(27, minHeap.size());

        minHeap.add(103);
        expected[28] = 103;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(28, minHeap.size());

        minHeap.add(104);
        expected[29] = 104;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(29, minHeap.size());

        minHeap.add(105);
        expected[30] = 105;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(30, minHeap.size());

        minHeap.add(106);
        expected[31] = 106;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(31, minHeap.size());

        minHeap.add(200);
        expected = new Integer[64];
        expected[1] = 1;
        expected[2] = 3;
        expected[3] = 2;
        expected[4] = 20;
        expected[5] = 7;
        expected[6] = 9;
        expected[7] = 11;
        expected[8] = 26;
        expected[9] = 24;
        expected[10] = 48;
        expected[11] = 23;
        expected[12] = 12;
        expected[13] = 15;
        expected[14] = 17;
        expected[15] = 14;
        expected[16] = 35;
        expected[17] = 29;
        expected[18] = 31;
        expected[19] = 42;
        expected[20] = 54;
        expected[21] = 69;
        expected[22] = 41;
        expected[23] = 70;
        expected[24] = 28;
        expected[25] = 100;
        expected[26] = 101;
        expected[27] = 102;
        expected[28] = 103;
        expected[29] = 104;
        expected[30] = 105;
        expected[31] = 106;
        expected[32] = 200;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(32, minHeap.size());

        expected = new Integer[16];
        minHeap.clear();
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(0, minHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testHeapRemove() {
        assertTrue(minHeap.isEmpty());

        minHeap.add(1);
        minHeap.add(3);
        minHeap.add(2);
        minHeap.add(20);
        minHeap.add(7);
        minHeap.add(9);
        minHeap.add(11);
        minHeap.add(26);
        minHeap.add(24);
        minHeap.add(48);
        Integer[] expected = {null, 1, 3, 2, 20, 7, 9, 11, 26, 24, 48, null, null
                , null, null, null};
        assertFalse(minHeap.isEmpty());
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(10, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 2;
        expected[3] = 9;
        expected[6] = 48;
        expected[10] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(9, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 3;
        expected[2] = 7;
        expected[5] = 24;
        expected[9] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(8, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 7;
        expected[2] = 20;
        expected[4] = 26;
        expected[8] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(7, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 9;
        expected[3] = 11;
        expected[7] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(6, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 11;
        expected[3] = 48;
        expected[6] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(5, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 20;
        expected[2] = 24;
        expected[5] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(4, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 24;
        expected[2] = 26;
        expected[4] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(3, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 26;
        expected[2] = 48;
        expected[3] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(2, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = 48;
        expected[2] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(1, minHeap.size());

        assertEquals(expected[1], minHeap.remove());
        expected[1] = null;
        assertArrayEquals(expected, minHeap.getBackingArray());
        assertEquals(0, minHeap.size());
        assertTrue(minHeap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testPriorityQueue() {
        minPriorityQueue.enqueue(43);
        minPriorityQueue.enqueue(89);
        minPriorityQueue.enqueue(17);
        minPriorityQueue.enqueue(64);
        minPriorityQueue.enqueue(5);

        assertEquals(new Integer(5), minPriorityQueue.dequeue());
        assertEquals(new Integer(17), minPriorityQueue.dequeue());
        assertEquals(3, minPriorityQueue.size());
        assertFalse(minPriorityQueue.isEmpty());
        assertEquals(new Integer(43), minPriorityQueue.dequeue());
        assertEquals(new Integer(64), minPriorityQueue.dequeue());
        assertEquals(new Integer(89), minPriorityQueue.dequeue());
        assertTrue(minPriorityQueue.isEmpty());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapAddException() {
        minHeap.add(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testHeapRemoveException() {
        minHeap.clear();
        assertTrue(minHeap.isEmpty());
        minHeap.remove();
    }
}