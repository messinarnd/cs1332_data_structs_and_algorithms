import java.util.Set;
import java.util.List;

/**
 * Interface for a HashMap.
 * DO NOT EDIT THIS FILE!
 *
 * @version 1.0
 * @author CS 1332 TAs
 */
public interface HashMapInterface<K, V> {
    int INITIAL_CAPACITY = 13;
    double MAX_LOAD_FACTOR = 0.67;

    /**
     * Adds the given key-value pair to the HashMap.
     * If an entry in the HashMap already has this key, replace the entry's
     * value with the new one passed in.
     *
     * In the case of a collision, use linear probing as your resolution
     * strategy.
     *
     * Check to see if the backing array needs to be regrown BEFORE adding. For
     * example, if my HashMap has a backing array of length 5, and 3 elements in
     * it, I should regrow at the start of the next add operation (even if it
     * is a key that is already in the hash map). This means you must account
     * for the data pending insertion when calculating the load factor.
     *
     * When regrowing, increase the length of the backing table by
     * 2 * old length + 1. Use the resizeBackingTable method.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @throws IllegalArgumentException if key or value is null
     * @return null if the key was not already in the map.  If it was in the
     * map, return the old value associated with it
     */
    V put(K key, V value);

    /**
     * Removes the entry with a matching key from the HashMap.
     *
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key does not exist
     * @return the value previously associated with the key
     */
    V remove(K key);

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key is not in the map
     * @return the value associated with the given key
     */
    V get(K key);

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @return whether or not the key is in the map
     */
    boolean containsKey(K key);

    /**
     * Clears the table and resets it to the default length.
     */
    void clear();

    /**
     * Returns the number of elements in the map.
     *
     * @return number of elements in the HashMap
     */
    int size();

    /**
     * Returns a Set view of the keys contained in this map.
     * Use {@code java.util.HashSet}.
     *
     * @return set of keys in this map
     */
    Set<K> keySet();

    /**
     * Returns a List view of the values contained in this map.
     * beginning with the first index of the backing array.
     * Use any class that implements the List interface
     * This includes {@code java.util.ArrayList} and
     * {@code java.util.LinkedList}.
     *
     * @return list of values in this map
     */
    List<V> values();

    /**
     * Resize the backing table to {@code length}.
     *
     * After resizing, the table's load factor is permitted to exceed
     * MAX_LOAD_FACTOR. No adjustment to the backing table's length is necessary
     * should this occur.
     *
     * Remember that you cannot just simply copy the entries over to the new
     * array.
     *
     * @param length new length of the backing table
     * @throws IllegalArgumentException if length is non-positive or less than
     * the number of items in the hash map.
     */

    void resizeBackingTable(int length);

    /**
     * DO NOT USE THIS METHOD IN YOUR CODE.  IT IS FOR TESTING ONLY.
     *
     * @return the backing array of the data structure, not a copy.  INCLUDE
     * EMPTY SPACES
     */
    MapEntry<K, V>[] getTable();
}
