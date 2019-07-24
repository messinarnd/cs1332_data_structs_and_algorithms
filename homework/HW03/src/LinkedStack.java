import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack.
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("There were no elements in the stack to return.");
        } else if (size == 1) {
            T returnedData = head.getData();
            head = null;
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
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data given was null. Provide real data.");
        } else if (size == 0) {
            LinkedNode<T> addedNode = new LinkedNode<>(data, null);
            head = addedNode;
            size++;
        } else {
            LinkedNode<T> addedNode = new LinkedNode<>(data, head);
            head = addedNode;
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
     * Returns the head of this stack.
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
}