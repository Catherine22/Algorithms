package com.catherine.pq;

/**
 * @author : Catherine
 * An implementation of symbol table
 */
public class FrequencyCounter {
    public int cal(int minlen, String[] array) {
        ST<String, Integer> st = new ST<>();

        for (String word : array) {
            if (word.length() < minlen) {
                continue; // ignore short strings
            }
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
//        System.out.printf("max: %d \n", st.get(max));
        return st.get(max);
    }
}
