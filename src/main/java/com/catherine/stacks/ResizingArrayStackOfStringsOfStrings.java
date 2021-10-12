package com.catherine.stacks;

import java.util.Arrays;

/**
 * @author : Catherine
 */
public class ResizingArrayStackOfStringsOfStrings extends StackOfStrings {
    private String[] s;
    private int n; // where we are in the String[] s

    public ResizingArrayStackOfStringsOfStrings(int capacity) {
        s = new String[capacity];
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Double the array while the array is full.
     *
     * @param item
     */
    @Override
    public void push(String item) {
        if (n == s.length) {
            resize(2 * s.length);
        }
        s[n++] = item;
    }

    /**
     * Halve the array when the array has reduced to 25% full.
     * In this case, we do not shrink the array when array is half full.
     * Because the worst case of the above scenario is to perform pop-push-pop-push...,
     * the array keeps resizing.
     *
     * @return
     */
    @Override
    public String pup() {
        String item = s[--n];
        s[n] = null;
        if (n > 0 && n == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        s = Arrays.copyOf(s, capacity);
    }
}
