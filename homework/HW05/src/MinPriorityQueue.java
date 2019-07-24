import java.util.NoSuchElementException;

/**
 * Your implementation of a min priority queue.
 * 
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class MinPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MinPriorityQueue() {
        backingHeap = new MinHeap<>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("The item given was null.");
        }
        backingHeap.add(item);
    }

    @Override
    public T dequeue() {
        if (backingHeap.size() == 0) {
            throw new NoSuchElementException("There were no items in the"
                    + "heap to deque.");
        }
        return backingHeap.remove();
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.isEmpty();
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap.clear();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap;
    }

}
