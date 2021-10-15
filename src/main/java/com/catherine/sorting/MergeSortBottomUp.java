package com.catherine.sorting;


import java.util.Arrays;

/**
 * Merge sort without recursion
 *
 * @param <T>
 * @author : Catherine
 */
public class MergeSortBottomUp<T extends Comparable<? super T>> {

    public void sort(Comparable<T> a[]) {
        Comparable<T>[] aux = Arrays.copyOf(a, a.length);
        int N = a.length;
        for (int sz = 1; sz < a.length; sz = sz + sz) {
            for (int lo = 0; lo < (N - sz); lo += sz + sz) {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
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
     * @param aux the auxiliary array, an empty array
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
