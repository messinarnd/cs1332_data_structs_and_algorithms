import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed queue.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        back = 0;
        size = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you must not
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("There were no elements in the queue to return.");
        } else if (size == 1) {
            T returnedData = backingArray[front];
            backingArray[front] = null;
            size--;
            return returnedData;
        } else {
            T returnedData = backingArray[front];
            backingArray[front] = null;
            if (front == backingArray.length - 1) {
                front = 0;
            } else {
                front++;
            }
            size--;
            return returnedData;
        }
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data was given. Provide real data.");
        } else {
            if (size == backingArray.length) {
                regrowArray();
            }
            if (back == backingArray.length - 1) {
                back = 0;
                backingArray[back] = data;
                size++;
            } else if (size == 0) {
                backingArray[back] = data;
                size++;
            } else {
                backingArray[++back] = data;
                size++;
            }
        }
        System.out.println(backingArray);
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

    /**
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Doubles the size of the backing array. After doing this, the method
     * positions the front of the backing array back at position 0.
     */
    private void regrowArray() {
        T[] temp = (T[]) new Object[backingArray.length * 2];
        int ndx = 0;
        if (back < front) {
            for (int i = front; i < backingArray.length; i++) {
                temp[ndx++] = backingArray[i];
            }
            for (int i = 0; i <= back; i++) {
                temp[ndx++] = backingArray[i];
            }
        } else {
            for (int i = front; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
        }
        backingArray = temp;
        front = 0;
        back = size - 1;
    }
}