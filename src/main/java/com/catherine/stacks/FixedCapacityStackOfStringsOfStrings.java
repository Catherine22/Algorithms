package com.catherine.stacks;

/**
 * @author : Catherine
 */
public class FixedCapacityStackOfStringsOfStrings extends StackOfStrings {
    private String[] s;
    private int n; // where we are in the String[] s

    public FixedCapacityStackOfStringsOfStrings(int capacity) {
        s = new String[capacity];
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void push(String item) {
        s[n++] = item;
    }

    @Override
    public String pup() {
        String item = s[--n];
        s[n] = null;
        return item;
    }
}
