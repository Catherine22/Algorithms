package com.catherine.sorting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

/**
 * @author : Catherine
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
interface Settings {
    String[] a1 = {"A", "G", "L", "O", "R", "H", "I", "M", "S", "T"};
    Integer[] a2 = {4, 2, 6, 8, 1, 5, 3, 9};
    Integer[] a3 = {4, 2, 3, 6, 4, 2, 3, 6, 4, 1, 3, 6};
    String[] sortedA1 = Arrays.copyOf(a1, a1.length);
    Integer[] sortedA2 = Arrays.copyOf(a2, a2.length);
    Integer[] sortedA3 = Arrays.copyOf(a3, a3.length);

    @BeforeAll
    static void beforeAllTests() {
        Arrays.sort(sortedA1);
        Arrays.sort(sortedA2);
        Arrays.sort(sortedA3);
    }
}
