package com.catherine.pq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author : Catherine
 */
public class FrequencyCounterTest {
    private FrequencyCounter fc;

    @BeforeEach
    void setUp() {
        fc = new FrequencyCounter();
    }

    @Test
    @DisplayName("Correctness tests")
    void cal() {
        String[] array = "the day is sunny the the the sunny is is".split(" ");
        int min = 2;
        int max = fc.cal(min, array);
        Assertions.assertEquals(4, max);

        min = 3;
        max = fc.cal(min, array);
        Assertions.assertEquals(4, max);

        min = 4;
        max = fc.cal(min, array);
        Assertions.assertEquals(2, max);

        min = 5;
        max = fc.cal(min, array);
        Assertions.assertEquals(2, max);

        min = 6;
        max = fc.cal(min, array);
        Assertions.assertEquals(0, max);
    }
}
