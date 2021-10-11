package com.catherine.dynamicconnectivity;

/**
 * @author : Catherine
 * @created : 11/10/2021
 */
public class QuickUnion implements UF {
    private final int[] id;

    public QuickUnion(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    /**
     * Chase parent pointers until reach root
     * Deep of i array accesses
     *
     * @param i
     * @return
     */
    private int root(int i) {
        int root = id[i];
        while (i != root) {
            root = id[root];
        }
        return root;
    }

    /**
     * Change the root of p to the root of q
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        id[root(p)] = root(q);
    }

    /**
     * Check if p and q have the same root
     * Deep of p and q array accesses
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
