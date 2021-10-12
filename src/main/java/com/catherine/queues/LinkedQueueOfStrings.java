package com.catherine.queues;

/**
 * @author : Catherine
 */
public class LinkedQueueOfStrings extends QueueOfStrings {
    private static class Node {
        String item;
        Node next;

        Node(String item) {
            this.item = item;
        }
    }

    private Node first, last;

    @Override
    void enqueue(String item) {
        Node oldLast = last;
        last = new Node(item);
        if (isEmpty()) { // Iff we had an empty queue.
            first = oldLast;
        } else {
            oldLast.next = last;
        }
    }

    @Override
    String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) { // Iff we had only one element in queue.
            last = null;
        }
        return item;
    }

    @Override
    boolean isEmpty() {
        return first == null;
    }
}
