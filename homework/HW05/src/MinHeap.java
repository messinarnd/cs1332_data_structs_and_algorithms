import java.util.NoSuchElementException;

/**
 * Your implementation of a min heap.
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("The item given was null. Can't "
                    + "add null data.");
        }
        if (size == backingArray.length - 1) {
            regrow();
        }
        backingArray[++size] = item;
        if (size != 1 && size != 0) {
            heapifyUp(size);
        }
    }

    /**
     * This method is used when adding an element to make sure
     * the heap stays in order with the smallest values being
     * closest to the root. Starts at end and works towards the root.
     * @param i An int representing the index of the current data being checked
     */
    private void heapifyUp(int i) {
        if (i > 1) {
            int iOfPar = i / 2;
            if (backingArray[iOfPar].compareTo(backingArray[i]) > 0) {
                T temp = backingArray[iOfPar];
                backingArray[iOfPar] = backingArray[i];
                backingArray[i] = temp;
                heapifyUp(iOfPar);
            }
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("There are no elements in the "
                    + "heap to be removed.");
        } else if (size == 1) {
            T removedData = backingArray[1];
            backingArray[1] = null;
            size--;
            return removedData;
        } else {
            T removedData = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            heapifyDown(1);
            return removedData;
        }
    }

    /**
     * This method is used when removing an element to make sure
     * the heap stays in order with the smallest values being
     * closest to the root. Starts at root and works down.
     * @param i An int representing the index of the current data being checked
     */
    private void heapifyDown(int i) {
        int left = 2 * i;
        int right = (2 * i) + 1;
        int curr = i;
        T temp = backingArray[i];
        if ((left <= size) && (backingArray[left].compareTo(temp) < 0)) {
            temp = backingArray[left];
            curr = left;
        }
        if (right <= size && (backingArray[right].compareTo(temp)) < 0) {
            temp = backingArray[right];
            curr = right;
        }
        if (curr != i) {
            backingArray[curr] = backingArray[i];
            backingArray[i] = temp;
            heapifyDown(curr);
        }
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * This method is used to regrow the backing array to twice the
     * original size.
     */
    private void regrow() {
        T[] temp = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 0; i <= size; i++) {
            temp[i] = backingArray[i];
        }
        backingArray = temp;
    }
}
