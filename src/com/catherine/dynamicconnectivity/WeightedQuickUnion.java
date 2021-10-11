package com.catherine.dynamicconnectivity;

/**
 * @author : Catherine
 * @created : 11/10/2021
 */
public class WeightedQuickUnion implements UF {
    private final int[] id;
    private final int[] sz; // this array keeps each size of each node

    public WeightedQuickUnion(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            sz[i] = 1;
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
     * Instead of changing the root of p to the root of q, it compares the size of each tree first.
     * Merging the root of the smaller tree to the other one.
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) {
            return;
        }

        if (sz[i] > sz[j]) {
            id[j] = i;
            sz[j] += sz[i];
        } else {
            id[i] = j;
            sz[i] += sz[j];
        }
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
