package com.catherine.dynamicconnectivity;

/**
 * @author : Catherine
 * @created : 09/10/2021
 * <p>
 * Union-Find
 */
interface UF {
    void union(int p, int q);

    boolean connected(int p, int q);
}
