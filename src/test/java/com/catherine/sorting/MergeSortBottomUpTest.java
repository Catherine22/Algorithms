package com.catherine.sorting;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author : Catherine
 */
class MergeSortBottomUpTest {

    @org.junit.jupiter.api.Test
    void sort() {
        MergeSortBottomUp<String> strCtx = new MergeSortBottomUp<>();
        String[] a1 = {"A", "G", "L", "O", "R", "H", "I", "M", "S", "T"};
        String[] sortedA1 = Arrays.copyOf(a1, a1.length);

        Arrays.sort(sortedA1);
        strCtx.sort(a1);
        assertArrayEquals(sortedA1, a1);

        MergeSortBottomUp<Integer> intCtx = new MergeSortBottomUp<>();
        Integer[] a2 = {4, 2, 6, 8, 1, 5, 3, 9};
        Integer[] sortedA2 = Arrays.copyOf(a2, a2.length);

        Arrays.sort(sortedA2);
        intCtx.sort(a2);
        assertArrayEquals(sortedA2, a2);
    }

}
