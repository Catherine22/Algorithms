package com.catherine.pq;

/**
 * @author : Catherine
 */
public class BinaryHeap<T extends Comparable<? super T>> {
    private int n; // size
    private T[] pq;

    public BinaryHeap(int capacity) {
        pq = (T[]) new Object[capacity + 1]; // because root starts from pq[1]
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public T get(int i) {
        return pq[i + 1];
    }

    /**
     * Add a node at the end, then swim it up.
     *
     * @param node
     */
    public void insert(T node) {
        pq[n++] = node;
        swim(n);
    }

    /**
     * Exchange the root with the node at end, then sink it down.
     *
     * @return the deleted node
     */
    public T delMax() {
        T max = pq[1];
        exch(1, n--);
        sink(1);

        pq[n + 1] = null; // prevent loitering
        return max;
    }

    /**
     * Check if the node is larger than its parent. If so, exchange the two node.
     * Repeat the exchange up to the root
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }


    /**
     * Check if pq[k]'s one (or both) children are larger than pq[k]. If so, exchange pq[k] and its larger child.
     * Repeat until the heap order resorted.
     *
     * @param k
     */
    private void sink(int k) {
        int j;
        while (2 * k <= n) {
            j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (less(j, k)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * Array helper function
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * Array helper function
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
