package com.catherine.sorting;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author : Catherine
 * @created : 27/09/2021
 */
class MergeSortTest {
    MergeSort<Integer> context = new MergeSort<>();

    @org.junit.jupiter.api.Test
    void sort() {
        Integer[] a1 = {4, 2, 7, 1, 4, 9, 4, 3};
        Integer[] sortedA1 = Arrays.copyOf(a1, a1.length);
        Arrays.sort(sortedA1, Collections.reverseOrder());
        assertArrayEquals(sortedA1, context.sort(a1, false));

        Integer[] a2 = {1, 2, 3, 4, 8, 4, 2, 9};
        Integer[] sortedA2 = Arrays.copyOf(a2, a2.length);
        Arrays.sort(sortedA2, Collections.reverseOrder());
        assertArrayEquals(sortedA2, context.sort(a2, false));

        Integer[] a3 = {};
        Integer[] sortedA3 = Arrays.copyOf(a3, a3.length);
        Arrays.sort(sortedA3);
        assertArrayEquals(sortedA3, context.sort(a3));

        Integer[] a4 = {4, 2, 5, 10, 11, 12, 13};
        Integer[] sortedA4 = Arrays.copyOf(a4, a4.length);
        Arrays.sort(sortedA4);
        assertArrayEquals(sortedA4, context.sort(a4, true));
    }

}
