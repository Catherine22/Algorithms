package com.catherine.queues;

/**
 * @author : Catherine
 */
public abstract class QueueOfStrings {

    /**
     * Insert a new item onto queue (from the end of the array).
     *
     * @param item
     */
    abstract void enqueue(String item);

    /**
     * Remove and return the String least recently added.
     * (Remove the item at the top of the array).
     *
     * @return
     */
    abstract String dequeue();

    abstract boolean isEmpty();

}
