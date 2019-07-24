/**
 * Your implementation of an ArrayList.
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data given was null.");
        }
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index given.");
        }
        if (backingArray.length == size) {
            growArray(data, index);
            size++;
            return;
        }
        if (index == size) {
            backingArray[size] = data;
        }

        for (int i = size; i > index; i--) {
            backingArray[i] = backingArray[i - 1];
        }

        backingArray[index] = data;
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data given was null.");
        }

        if (backingArray[0] == null) {
            backingArray[0] = data;
            size++;
            return;
        }
        if (backingArray.length == size) {
            growArray(data, 0);
            size++;
            return;
        } else {
            for (int i = size; i > 0; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[0] = data;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data given was null.");
        }
        if (backingArray.length == size) {
            growArray(data, size);
            size++;
            return;
        } else {
            backingArray[size] = data;
        }

        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index given.");
        }
        if (index == size - 1) {
            return removeFromBack();
        }

        T data = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;

        size--;
        return data;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T front = backingArray[0];
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
            //backingArray[i + 1] = null;
        }
        backingArray[size - 1] = null;
        size--;
        return front;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }

        T back = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return back;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index given.");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        T[] newArray = (T[]) new Object[INITIAL_CAPACITY];
        backingArray = newArray;
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    /**
     * Doubles the size of the array.
     */
    private void growArray(T data, int index) {
        T[] newArray = (T[]) new Object[size * 2];
        if (index == 0) {
            newArray[0] = data;
            for (int i = 1; i <= size; i++) {
                newArray[i] = backingArray[i - 1];
            }
        } else if (index == size) {
            newArray[size] = data;
            for (int i = 0; i < size; i++) {
                newArray[i] = backingArray[i];
            }
        } else {
            for (int i = 0; i < index; i++) {
                newArray[i] = backingArray[i];
            }
            newArray[index] = data;
            for (int j = index + 1; j <= size; j++) {
                newArray[j] = backingArray[j - 1];
            }
        }
        backingArray = newArray;
        return;
    }
}