package com.catherine.sorting;

import com.catherine.sorting.impl.DuplicateKeys;
import com.catherine.sorting.impl.Selection;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Catherine
 */
class QuicksortTest implements Settings {

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
        Selection<String> strCtx = new Selection<>();
        int k = random(0, a1.length - 1);
        assertEquals(strCtx.selection(a1, k), sortedA1[k]);


        Selection<Integer> intCtx = new Selection<>();
        k = random(0, a2.length - 1);
        assertEquals(intCtx.selection(a2, k), sortedA2[k]);
    }

    @Test
    void duplicateKeys() {
        DuplicateKeys<String> strCtx = new DuplicateKeys<>();
        strCtx.sort(a1);
        assertArrayEquals(sortedA1, a1);

        DuplicateKeys<Integer> intCtx = new DuplicateKeys<>();
        intCtx.sort(a2);
        assertArrayEquals(sortedA2, a2);

        intCtx.sort(a3);
        assertArrayEquals(sortedA3, a3);
    }

    private int random(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

}
