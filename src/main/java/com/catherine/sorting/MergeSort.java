package com.catherine.sorting;

import java.util.Arrays;

/**
 * @param <T>
 * @author : Catherine
 */
public class MergeSort<T extends Comparable<? super T>> extends Sort<T> {

    @Override
    public T[] sort(T[] array) {
        return sort(array, true);
    }

    @Override
    public T[] sort(T[] array, boolean isAscending) {
        T[] sortedArray = Arrays.copyOf(array, array.length);
        return sort(sortedArray, 0, sortedArray.length, isAscending);
    }

    /**
     * @param array an array to be sorted
     * @param from  set a range of the given array to be sorted
     * @param to    set a range of the given array to be sorted
     * @return
     */
    private T[] sort(T[] array, int from, int to, boolean isAscending) {
        if (to - from < 2) {
            return array;
        }
        int mid = (from + to) / 2;
        sort(array, from, mid, isAscending);
        sort(array, mid, to, isAscending);
        return merge(array, from, mid, to, isAscending);
    }

    /**
     * @param array
     * @param from:       where the array on the left starts from
     * @param mid:        where the array on the right starts from
     * @param to:         where the array on the right ends
     * @param isAscending
     * @return
     */
    private T[] merge(T[] array, int from, int mid, int to, boolean isAscending) {
        // lh: header in the left group;
        // rh: header in the right group;
        // h: header in the sorted array
        int lh = from, rh = mid, h = from;
        T n1, n2;
        T[] copy = Arrays.copyOf(array, array.length);
        while (lh < mid && rh < to) {
            n1 = copy[lh];
            n2 = copy[rh];

            if (isAscending) {
                if (n1.compareTo(n2) > 0) {
                    array[h++] = n2;
                    rh++;
                } else {
                    array[h++] = n1;
                    lh++;
                }
            } else {
                if (n1.compareTo(n2) < 0) {
                    array[h++] = n2;
                    rh++;
                } else {
                    array[h++] = n1;
                    lh++;
                }
            }
        }

        while (h < to) {
            if (lh < mid) {
                array[h++] = copy[lh++];
            }

            if (rh < to) {
                array[h++] = copy[rh++];
            }
        }

        return array;
    }
}
