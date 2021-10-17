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
    String[] sortedA1 = Arrays.copyOf(a1, a1.length);
    Integer[] sortedA2 = Arrays.copyOf(a2, a2.length);

    @BeforeAll
    static void beforeAllTests() {
        Arrays.sort(sortedA1);
        Arrays.sort(sortedA2);
    }
}
