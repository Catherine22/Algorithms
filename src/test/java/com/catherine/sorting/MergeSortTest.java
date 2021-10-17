package com.catherine.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author : Catherine
 */
class MergeSortTest implements Settings {

    @Test
    void sort() {
        MergeSort<String> strCtx = new MergeSort<>();
        strCtx.sort(a1);
        assertArrayEquals(sortedA1, a1);

        MergeSort<Integer> intCtx = new MergeSort<>();
        intCtx.sort(a2);
        assertArrayEquals(sortedA2, a2);
    }

}
