/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index cannot be less "
                    + "than 0 or greater than the size of the list.");
        } else if (data == null) {
            throw new IllegalArgumentException("The given data was null. "
                    + "Cannot add null data to the list.");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> thisNode = new LinkedListNode<>(data, null, null);
            if (index <= size / 2) {
                LinkedListNode<T> curr = head;
                for (int i = 0; i < index - 1; i++) {
                    curr = curr.getNext();
                }
                thisNode.setNext(curr.getNext());
                thisNode.getNext().setPrevious(thisNode);
                curr.setNext(thisNode);
                thisNode.setPrevious(curr);
                size++;
            } else {
                LinkedListNode<T> curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious();
                }
                thisNode.setPrevious(curr.getPrevious());
                thisNode.getPrevious().setNext(thisNode);
                curr.setPrevious(thisNode);
                thisNode.setNext(curr);
                size++;
            }
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The given data was null. "
                    + "Cannot add null data to the list.");
        }
        if (size == 0) {
            LinkedListNode<T> firstNode = new LinkedListNode<>(data,
                    null, null);
            head = firstNode;
            tail = firstNode;
            size++;
        } else {
            LinkedListNode<T> firstNode = new LinkedListNode<>(data,
                    null, head);
            head.setPrevious(firstNode);
            head = firstNode;
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The given data was null. "
                    + "Cannot add null data to the list.");
        }
        if (size == 0) {
            LinkedListNode<T> lastNode = new LinkedListNode<>(data, null, null);
            head = lastNode;
            tail = lastNode;
            size++;
        } else {
            LinkedListNode<T> lastNode = new LinkedListNode<>(data, tail, null);
            tail.setNext(lastNode);
            tail = lastNode;
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index cannot be less "
                    + "than 0 or greater than or equal to the size of the list.");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> curr;
            if (index <= (size / 2) - 1) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext();
                }
            } else {
                curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious();
                }
            }
            T removedData = curr.getData();
            curr.getPrevious().setNext(curr.getNext());
            curr.getNext().setPrevious(curr.getPrevious());
            curr.setNext(null);
            curr.setPrevious((null));
            size--;
            return removedData;
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T removedData = head.getData();
            clear();
            return removedData;
        } else {
            T removedData = head.getData();
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return removedData;
        }
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T removedData = tail.getData();
            head = null;
            tail = null;
            size--;
            return removedData;
        } else {
            T removedData = tail.getData();
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
            return removedData;
        }
    }

    @Override
    public boolean removeFirstOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The given data was null. "
                    + "Cannot add null data to the list.");
        } else if (size == 0) {
            return false;
        } else if (head.getData().equals(data)) {
            removeFromFront();
            return true;
        } else {
            LinkedListNode<T> curr = head;
            while (curr.getNext() != null) {
                if (curr.getNext() == tail && tail.getData().equals(data)) {
                    tail = curr;
                    tail.setNext(null);
                    size--;
                    return true;
                }
                if (curr.getNext().getData().equals(data)) {
                    curr.setNext(curr.getNext().getNext());
                    curr.getNext().setPrevious(curr);
                    size--;
                    return true;
                }
                curr = curr.getNext();
            }
            return false;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index cannot be less "
                    + "than 0 or greater or equal to the size of the list.");
        } else if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getData();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] myArr = new Object[size];
        LinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            myArr[i] = curr.getData();
            curr = curr.getNext();
        }
        return myArr;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}