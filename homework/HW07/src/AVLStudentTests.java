import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * These tests are not exhaustive.
 * @version 1.0
 */


public class AVLStudentTests {

    private static final int TIMEOUT = 200;
    private AVL<Integer> tree;

    @Before
    public void setup() {
        tree = new AVL<Integer>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullElement() {
        Integer[] nullElement = {1, 2, 3, new Integer(null), 4};
        tree = new AVL<>(Arrays.asList(nullElement));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullCollection() {
        tree = new AVL<>(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRootHeight() {
        Integer[] singleValue = {1};
        Integer[] rightChildLeftNull = {3, 5};
        Integer[] leftChildRightNull = {3, 1};
        Integer[] superRightHeavy = {1, 2, 3, 4, 5};
        Integer[] superLeftHeavy = {5, 4, 3, 2, 1};

        tree = new AVL<Integer>(Arrays.asList(singleValue));
        assertEquals(0, tree.height());
        tree = new AVL<>(Arrays.asList(rightChildLeftNull));
        assertEquals(1, tree.height());
        tree = new AVL<>(Arrays.asList(leftChildRightNull));
        assertEquals(1, tree.height());
        tree = new AVL<>();
        assertEquals(-1, tree.height());
        tree = new AVL<>(Arrays.asList(superRightHeavy));
        assertEquals(2, tree.height());
        tree = new AVL<>(Arrays.asList(superLeftHeavy));
        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testDescending() {
        Integer[] list = {7, 10, 15, 4, 8, 13, 20};
        Integer[] answer = {20, 13, 8, 4};
        tree = new AVL<>(Arrays.asList(list));
        assertArrayEquals(answer, tree.listLeavesDescending().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddRepeat() {
        Integer[] list = {1, 1, 1, 1, 1, 1};
        tree = new AVL<>(Arrays.asList(list));
        assertEquals(1, tree.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveRepeat() {
        Integer[] list = {1, 2, 3, 4};
        tree = new AVL<>(Arrays.asList(list));
        assertEquals(new Integer(4), tree.remove(4));
        assertEquals(3, tree.size());
        tree.remove(4);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFromEmpty() {
        tree = new AVL<>();
        tree.remove(new Integer(2));
    }



    /**
     * If you are failing this test case, you must return the data found in the
     * node, not the data passed into the method. Refer to the docs of
     * AVLInterface for clarification.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveCorrectData() {
        AVL<DudeMan> tree = new AVL<DudeMan>();
        tree.add(new DudeMan(4, 3, "John Smoke"));
        DudeMan test = tree.remove(new DudeMan(4, 3, "not John Smoke"));
        assertEquals("John Smoke", test.getName());
    }

    /**
     * If you are failing this test case, you must return the data found in the
     * node, not the data passed into the method. Refer to the docs of
     * AVLInterface for clarification.
     */
    @Test(timeout = TIMEOUT)
    public void testGetCorrectData() {
        AVL<DudeMan> tree = new AVL<DudeMan>();
        tree.add(new DudeMan(4, 3, "John Smoke"));
        DudeMan test = tree.get(new DudeMan(4, 3, "not John Smoke"));
        assertEquals("John Smoke", test.getName());
    }


    private class DudeMan implements Comparable<DudeMan> {
        private int height;
        private int age;
        private String name;

        /**
         * This is a class I'm using in attempt to break your tree
         * @param height the height of dudeman
         * @param age the age of dudeman
         * @param name dudeman's name
         */
        public DudeMan(int height, int age, String name) {
            this.height = height;
            this.name = name;
            this.age = age;
        }

        /**
         * asdasd
         * @return asdsa
         */
        public int getHeight() {
            return height;
        }

        /**
         * sadasd
         * @return asdsa
         */
        public int getAge() {
            return age;
        }

        /**
         * sadasd
         * @return sdasdsa
         */
        public String getName() {
            return name;
        }

        @Override
        public int compareTo(DudeMan o) {
            return (this.age + this.height) - (o.getAge() + o.getHeight());
        }

        @Override
        public boolean equals(Object o) {
            DudeMan other = (DudeMan) o;
            return (this.age + this.height) ==
                    (other.getAge() + other.getHeight());
        }
    }

}