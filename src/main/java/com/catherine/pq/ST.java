package com.catherine.pq;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : Catherine
 * ST, symbol table
 * <p>
 * Rules:
 * 1. Value cannot be null
 * 2. get() returns null if the key is not present
 * 3. put() overrides the old value with the new value
 * 4. The best practice is to use immutable types for symbol table keys
 * <p>
 * Those rules make sure we can delete and check the state of a value using lazy approaches.
 */
public class ST<T extends Comparable<? super T>, Object> {
    private Object[] array;
    private Map<T, Object> map;

    public ST() {
        map = new HashMap<>();
    }

    /**
     * Insert a new value or override the old value with the new value
     *
     * @param key
     * @param value
     */
    public void put(T key, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        map.put(key, value);
    }

    /**
     * @param key
     * @return null if the key is not present.
     */
    public Object get(T key) {
        return map.get(key);
    }

    /**
     * As an null value implies its key is not present, we can implement a lazy version of delete()
     *
     * @param key
     */
    public void delete(T key) {
        put(key, null);
    }

    /**
     * We use a lazy version to delete a value by associating a null value to its corresponding key.
     *
     * @param key
     * @return
     */
    public boolean contains(T key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        if (map.size() == 0) {
            return true;
        }

        Set<T> keySet = map.keySet();
        for (T key : keySet) {
            if (map.get(key) != null) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        if (map.size() == 0) {
            return 0;
        }

        int counts = 0;
        Set<T> keySet = map.keySet();
        for (T key : keySet) {
            if (map.get(key) != null) {
                counts++;
            }
        }
        return counts;
    }

    public Iterable<T> keys() {
        Set<T> keySet = map.keySet();
        keySet.removeIf(key -> map.get(key) == null);
        return keySet;
    }
}
