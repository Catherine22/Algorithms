package com.catherine.sorting;

import java.util.Arrays;

/**
 * best: n.
 * worst: n^2
 * avg.: n^2
 *
 * @author : Catherine
 * @param <T>
 */
public class InsertionSort<T extends Comparable<? super T>> extends Sort<T> {

    @Override
    public T[] sort(T[] array) {
        return sort(array, true);
    }

    @Override
    public T[] sort(T[] array, boolean isAscending) {
        T[] sortedArray = Arrays.copyOf(array, array.length);

        // len: length of the sorted array.
        // Insertion sort compares two values, the first value in the unsorted array and one value in the sorted array.
        // h1 points to a position in the sorted array (from right to left).
        // h2 points to the target to be compare.
        int len = 1, h1, h2;
        T n1, n2, temp;
        for (int i = len; i < sortedArray.length; i++) {
            h1 = i - 1;
            h2 = i;
            while (h1 >= 0) {
                n1 = sortedArray[h1];
                n2 = sortedArray[h2];
                if (isAscending) {
                    if (n1.compareTo(n2) > 0) {
                        temp = n1;
                        sortedArray[h1] = n2;
                        sortedArray[h2] = temp;
                        h2 = h1;
                    } else {
                        h2 = i;
                    }
                } else {
                    if (n1.compareTo(n2) < 0) {
                        temp = n1;
                        sortedArray[h1] = n2;
                        sortedArray[h2] = temp;
                        h2 = h1;
                    } else {
                        h2 = i;
                    }
                }
                h1--;
            }
            len++;
        }
        return sortedArray;
    }
}
