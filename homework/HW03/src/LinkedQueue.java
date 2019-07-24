import java.util.NoSuchElementException;

/**
 * Your implementation of a linked queue.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("There were no elements in the queue to return.");
        } else if (size == 1) {
            T returnedData = head.getData();
            head = null;
            tail = null;
            size--;
            return returnedData;
        } else {
            T returnedData = head.getData();
            head = head.getNext();
            size--;
            return returnedData;
        }
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data was given. Provide real data.");
        } else if (size == 0) {
            LinkedNode<T> addedNode = new LinkedNode<>(data, null);
            head = addedNode;
            tail = addedNode;
            size++;
        } else {
            LinkedNode<T> addedNode = new LinkedNode<>(data, null);
            tail.setNext(addedNode);
            tail = addedNode;
            size++;
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

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }
}