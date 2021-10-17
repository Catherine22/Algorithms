package com.catherine.sorting;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Catherine
 */
class QuickSortTest implements Settings {

    @Test
    void sort() {
        QuickSort<String> strCtx = new QuickSort<>();
        strCtx.sort(a1);
        assertArrayEquals(sortedA1, a1);

        QuickSort<Integer> intCtx = new QuickSort<>();
        intCtx.sort(a2);
        assertArrayEquals(sortedA2, a2);
    }

    @Test
    void selection() {
        com.catherine.sorting.impl.QuickSort<String> strCtx = new com.catherine.sorting.impl.QuickSort<>();
        int k = random(0, a1.length - 1);
        assertEquals(strCtx.selection(a1, k), sortedA1[k]);


        com.catherine.sorting.impl.QuickSort<Integer> intCtx = new com.catherine.sorting.impl.QuickSort<>();
        k = random(0, a2.length - 1);
        assertEquals(intCtx.selection(a2, k), sortedA2[k]);
    }

    private int random(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

}
