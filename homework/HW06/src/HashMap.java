import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Your implementation of HashMap.
 * 
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 9030023165
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}F.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        if ((key == null) || (value == null)) {
            throw new IllegalArgumentException("Either the value or the key "
                    + "given was null. Provide real data to add.");
        }

        MapEntry<K, V> thisEntry = new MapEntry<>(key, value);
        double loadFactor = (double) (size + 1) / table.length;
        if (loadFactor > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        int ndx = Math.abs(key.hashCode()) % table.length;
        int colCount = 0;
        int removedNdx = -1;

        while (colCount < table.length) {
            if (table[ndx] == null) {
                if (removedNdx == -1) {
                    table[ndx] = thisEntry;
                    size++;
                    return null;
                } else {
                    table[removedNdx] = thisEntry;
                    size++;
                    return null;
                }
            } else if (table[ndx].getKey().equals(key)
                    && !table[ndx].isRemoved()) {
                V removedVal = table[ndx].getValue();
                table[ndx].setValue(value);
                return removedVal;
            } else if (table[ndx].isRemoved() && removedNdx == -1) {
                removedNdx = ndx;
                ndx = ++ndx % table.length;
                colCount++;
            } else {
                colCount++;
                ndx = ++ndx % table.length;
            }
        }
        table[removedNdx] = thisEntry;
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The data given was null. "
                    + "Cannot remove null data.");
        } else if (size == 0) {
            throw new NoSuchElementException("There were no elements in "
                    + "the table to remove.");
        }

        int ndx = Math.abs(key.hashCode()) % table.length;
        while (table[ndx] != null) {
            if (table[ndx].getKey().equals(key)) {
                if (table[ndx].isRemoved()) {
                    ndx = ++ndx % table.length;
                } else {
                    table[ndx].setRemoved(true);
                    size--;
                    return table[ndx].getValue();
                }
            } else {
                ndx = ++ndx % table.length;
            }
        }
        throw new NoSuchElementException("The key given could not be found "
                + "in the table.");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key given was null. "
                    + "Cannot remove a search for a null key.");
        } else if (size == 0) {
            throw new NoSuchElementException("There are no elements in the "
                    + "table to search through.");
        }

        int ndx = Math.abs(key.hashCode()) % table.length;
        while (table[ndx] != null) {
            if (table[ndx].getKey().equals(key) && !table[ndx].isRemoved()) {
                return table[ndx].getValue();
            } else {
                ndx = ++ndx % table.length;
            }
        }
        throw new NoSuchElementException("The key given could not be"
                + "found in the table.");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key given was null. "
                    + "Cannot search for a null key.");
        }

        int ndx = Math.abs(key.hashCode()) % table.length;
        while (table[ndx] != null) {
            if (table[ndx].getKey().equals(key) && !table[ndx].isRemoved()) {
                return true;
            } else {
                ndx = ++ndx % table.length;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> mySet = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                mySet.add(table[i].getKey());
            }
        }
        return mySet;
    }

    @Override
    public List<V> values() {
        List<V> myList = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                myList.add(table[i].getValue());
            }
        }
        return myList;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException("The length given was not a "
                    + "positive integer. Must be positive integer to resize.");
        }

        MapEntry<K, V>[] temp = new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                int ndx = Math.abs(table[i].getKey().hashCode()) % temp.length;
                while (temp[ndx] != null) {
                    ndx = ++ndx % temp.length;
                }
                temp[ndx] = new MapEntry<>(table[i].getKey(),
                        table[i].getValue());
            }
        }
        table = temp;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }
}