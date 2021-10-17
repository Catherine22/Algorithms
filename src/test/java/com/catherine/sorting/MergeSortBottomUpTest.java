package com.catherine.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author : Catherine
 */
class MergeSortBottomUpTest implements Settings {

    @Test
    void sort() {
        MergeSortBottomUp<String> strCtx = new MergeSortBottomUp<>();
        strCtx.sort(a1);
        assertArrayEquals(sortedA1, a1);

        MergeSortBottomUp<Integer> intCtx = new MergeSortBottomUp<>();
        intCtx.sort(a2);
        assertArrayEquals(sortedA2, a2);
    }
}
