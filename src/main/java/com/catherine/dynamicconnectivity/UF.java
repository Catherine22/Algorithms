package com.catherine.dynamicconnectivity;

/**
 * @author : Catherine
 * <p>
 * Union-Find
 */
interface UF {
    void union(int p, int q);

    boolean connected(int p, int q);
}
