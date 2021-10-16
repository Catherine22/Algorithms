package com.catherine.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : Catherine
 */
public class QuickSort<T extends Comparable<? super T>> {

    public void sort(T[] a) {
        // shuffle is needed for performance guarantee
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(T[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi); // j is the correct position, no need to check j again.
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }


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

    /**
     * swap a[i] and a[j]
     */
    private void exch(T[] a, int i, int j) {
        T t; // temp
        t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private void shuffle(T[] a) {
        List<T> l = Arrays.asList(a);
        Collections.shuffle(l);
        l.toArray(a);
    }
}
