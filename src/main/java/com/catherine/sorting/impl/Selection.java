package com.catherine.sorting.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : Catherine
 * <p>
 * <h1>Selection</h1>
 * <h2>Quicksort</h2>
 * Given an array of N items, find a kth smallest item.
 * E.g., given a = {1, 3, 6, 4, 5} and k = 3, the answer is 4.
 */
public class Selection<T extends Comparable<? super T>> {

    /**
     * Instead of having two pointers i and j as we use in quicksort,
     * we only need a pointer which partitions the array into two subarrays - [ <= j ] [j] [ >= j ]
     * Then we compare k and j, and repeat partitioning in a subarray until j = k
     *
     * @param a
     * @param k
     * @return j (The kth smallest item)
     */
    public T selection(T[] a, int k) {
        // shuffle is needed for performance guarantee
        shuffle(a);
        int lo = 0;
        int hi = a.length - 1;
        int j;

        while (hi > lo) {
            j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }

    /**
     * Given an array a, its lowest position and highest position and return j
     * such that we partition the array into [j][ <= j ][ >= j ].
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private int partition(T[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            // a[i] - a[j-1] must be less than a[lo]
            while (a[++i].compareTo(a[lo]) < 0) {
                if (i == hi) {
                    break;
                }
            }

            // a[j+1] - a[hi] must be greater than a[lo]
            while (a[--j].compareTo(a[lo]) > 0) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }

        exch(a, lo, j); // swap with partitioning item
        return j; // return index of item now known to be in place
    }

    private void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private void shuffle(T[] a) {
        List<T> l = Arrays.asList(a);
        Collections.shuffle(l);
        l.toArray(a);
    }
}
