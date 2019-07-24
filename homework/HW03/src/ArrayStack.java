import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed stack.
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("There were no elements in the stack to return.");
        } else {
            T returnedData = backingArray[size - 1];
            size--;
            backingArray[size] = null;
            return returnedData;
        }
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data was given. Provide real data.");
        } else if (size == backingArray.length) {
            regrowArray();
        }
        backingArray[size] = data;
        size++;
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
     * Returns the backing array of this stack.
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

    private void regrowArray() {
        T[] temp = (T[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = backingArray[i];
        }
        backingArray = temp;
    }
}