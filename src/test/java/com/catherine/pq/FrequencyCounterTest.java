package com.catherine.pq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author : Catherine
 */
public class FrequencyCounterTest {
    FrequencyCounter fc = new FrequencyCounter();

    @Test
    void cal() {
        String[] array = "the day is sunny the the the sunny is is".split(" ");
        int min = 2;
        int max = fc.cal(min, array);
        Assertions.assertEquals(4, max);
    }
}
