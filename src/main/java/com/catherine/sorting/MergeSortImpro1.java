package com.catherine.sorting;


import java.util.Arrays;

/**
 * An improvement of merge sort using insertion sort
 *
 * @param <T>
 * @author : Catherine
 */
public class MergeSortImpro1<T extends Comparable<? super T>> {
    private final static int CUTOFF = 7;

    public void sort(Comparable<T> a[]) {
        Comparable<T>[] aux = Arrays.copyOf(a, a.length);
        sort(a, aux, 0, a.length - 1);
    }

    private void sort(Comparable<T> a[], Comparable<T> aux[], int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
//            InsertionSort.sort(a, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * For example, given an array a [0, 1, 2, 3, 4, 5], the auxiliary array is the copy of a.
     * This method splits aux into two subarrays. The first group starts from aux[lo] to aux[mid],
     * another group ranged from aux[mid + 1] to aux[hi]
     * <p>
     * A precondition for this method to work is that the subarrays of the given array must be sorted.
     * I.e., a[lo] - a[mid] and a[mid + 1] to a[hi]
     *
     * @param a   the original array, this array will be sorted
     * @param aux the auxiliary array, a copy of the original array a
     * @param lo  the lowest position
     * @param mid the middle position
     * @param hi  the highest position
     */
    @SuppressWarnings("unchecked")
    private void merge(Comparable<T> a[], Comparable<T> aux[], int lo, int mid, int hi) {
        int i = lo; // a pointer in subarray 1
        int j = mid + 1; // a pointer in subarray 2
        // copy the array a
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[i].compareTo((T) aux[j]) <= 0) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }
}
