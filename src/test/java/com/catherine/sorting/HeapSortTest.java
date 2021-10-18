package com.catherine.sorting;

import com.catherine.pq.HeapSort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author : Catherine
 */
class HeapSortTest implements Settings {

    @Test
    void sort() {
        HeapSort<String> strCtx = new HeapSort<>();
        strCtx.sort(a1);
        assertArrayEquals(sortedA1, a1);

        HeapSort<Integer> intCtx = new HeapSort<>();
        intCtx.sort(a2);
        assertArrayEquals(sortedA2, a2);

        intCtx.sort(a3);
        assertArrayEquals(sortedA3, a3);
    }

}
