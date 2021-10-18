package com.catherine.pq;

/**
 * @author : Catherine
 * <p>
 * NOTICE, Binary Heap is 1-based indexing, but heap sort is 0-based indexing.
 * <p>
 * @see HeapSortCopy to check how to implement the 1-based indexing heap sort.
 */
public class HeapSort<T extends Comparable<? super T>> {

    public void sort(T[] pq) {
        int n = pq.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }

        while (n > 1) {
            exch(pq, 0, --n);
            sink(pq, 1, n);
        }
    }


    /**
     * Check if pq[k]'s one (or both) children are larger than pq[k]. If so, exchange pq[k] and its larger child.
     * Repeat until the heap order resorted.
     * <p>
     * Notice: this function has been converted to the 0-based indexing
     *
     * @param k
     */
    private void sink(T[] pq, int k, int n) {
        int j;
        // suppose parent = k, then children = 2 * k + 1 and/or 2 * k + 2
        k = k - 1;
        while (2 * k + 2 <= n) {
            j = 2 * k + 1;
            if (j + 1 < n && less(pq, j, j + 1)) {
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
