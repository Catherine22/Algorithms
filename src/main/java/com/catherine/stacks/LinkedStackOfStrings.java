package com.catherine.stacks;

/**
 * @author : Catherine
 */
public class LinkedStackOfStrings extends MyStack {
    private static class Node {
        String item;
        Node next;

        Node(String item) {
            this.item = item;
        }
    }

    private Node first;

    public LinkedStackOfStrings() {
        this(null);
    }

    public LinkedStackOfStrings(Node first) {
        this.first = first;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void push(String item) {
        Node oldFirst = first;
        first = new Node(item);
        first.next = oldFirst;
    }

    @Override
    public String pup() {
        String item = first.item;
        first = first.next;
        return item;
    }
}
