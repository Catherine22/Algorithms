package com.catherine.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author : Catherine
 */
class BubbleSortTest {
    BubbleSort<Integer> context = new BubbleSort<>();

    @Test
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


    @Test
    void sort1() {
        Integer[] a1 = {4, 2, 7, 1, 4, 9, 4, 3};
        Integer[] sortedA1 = Arrays.copyOf(a1, a1.length);
        Arrays.sort(sortedA1);
        assertArrayEquals(sortedA1, context.sort1(a1, true));

        Integer[] a2 = {1, 2, 3, 4, 8, 5, 7, 9};
        Integer[] sortedA2 = Arrays.copyOf(a2, a2.length);
        Arrays.sort(sortedA2);
        assertArrayEquals(sortedA2, context.sort1(a2, true));

        Integer[] a3 = {};
        Integer[] sortedA3 = Arrays.copyOf(a3, a3.length);
        Arrays.sort(sortedA3);
        assertArrayEquals(sortedA3, context.sort1(a3, true));

        Integer[] a4 = {4, 2, 5, 10, 11, 12, 13};
        Integer[] sortedA4 = Arrays.copyOf(a4, a4.length);
        Arrays.sort(sortedA4);
        assertArrayEquals(sortedA4, context.sort1(a4, true));
    }

    @Test
    void sort2() {
        Integer[] a1 = {4, 2, 7, 1, 4, 9, 4, 3};
        Integer[] sortedA1 = Arrays.copyOf(a1, a1.length);
        Arrays.sort(sortedA1);
        assertArrayEquals(sortedA1, context.sort2(a1, true));

        Integer[] a2 = {1, 2, 3, 4, 8, 5, 7, 9};
        Integer[] sortedA2 = Arrays.copyOf(a2, a2.length);
        Arrays.sort(sortedA2);
        assertArrayEquals(sortedA2, context.sort2(a2, true));

        Integer[] a3 = {};
        Integer[] sortedA3 = Arrays.copyOf(a3, a3.length);
        Arrays.sort(sortedA3);
        assertArrayEquals(sortedA3, context.sort2(a3, true));

        Integer[] a4 = {4, 2, 5, 10, 11, 12, 13};
        Integer[] sortedA4 = Arrays.copyOf(a4, a4.length);
        Arrays.sort(sortedA4);
        assertArrayEquals(sortedA4, context.sort2(a4, true));
    }

}
