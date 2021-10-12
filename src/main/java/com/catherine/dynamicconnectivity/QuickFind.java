package com.catherine.dynamicconnectivity;

/**
 * @author : Catherine
 */
public class QuickFind implements UF {
    private final int[] id;

    public QuickFind(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    /**
     * Replace id[p] to id[q] as well as elements that shared the same value with id[p].
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
}
