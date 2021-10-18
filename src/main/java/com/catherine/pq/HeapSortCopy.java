package com.catherine.pq;

import java.util.Arrays;

/**
 * @author : Catherine
 * <p>
 * NOTICE, Binary Heap is 1-based indexing, but heap sort is 0-based indexing.
 * In this example, we create a copy of the priority queue so that we do not have to update all the helper functions.
 * But this approach is actually not an acceptable way to implement heap sort.
 * <p>
 * In fact, heap sort is an in-place sorting algorithm. No need extra space.
 */
public class HeapSortCopy<T extends Comparable<? super T>> {

    public void sort(T[] pq) {
        T[] copy = Arrays.copyOf(pq, pq.length + 1);
        copy[0] = null;
        System.arraycopy(pq, 0, copy, 1, pq.length);
        int n = pq.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(copy, k, n);
        }

        while (n > 1) {
            exch(copy, 1, n);
            sink(copy, 1, --n);
        }

        System.arraycopy(copy, 1, pq, 0, pq.length);
    }


    /**
     * Check if pq[k]'s one (or both) children are larger than pq[k]. If so, exchange pq[k] and its larger child.
     * Repeat until the heap order resorted.
     *
     * @param k
     */
    private void sink(T[] pq, int k, int n) {
        int j;
        while (2 * k <= n) {
            j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (less(pq, j, k)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    /**
     * Array helper function
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(T[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * Array helper function
     *
     * @param i
     * @param j
     */
    private void exch(T[] pq, int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
