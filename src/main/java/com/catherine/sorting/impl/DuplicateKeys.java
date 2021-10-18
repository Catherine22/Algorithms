package com.catherine.sorting.impl;

/**
 * @author : Catherine
 * <p>
 * <h1>3-way Partitioning</h1>
 * <h2>Quicksort</h2>
 * <p>
 * Huge numbers of duplicate keys
 */
public class DuplicateKeys<T extends Comparable<? super T>> {


    /**
     * When a given array has huge numbers of duplicate keys,
     * we use 3-way partitioning to partition the array into three parts so that
     * <p>
     * Before:
     * [ v |                      ]
     * lo                        hi
     * <p>
     * Duration:
     * [ < v ] [ = v ] [   ] [ > v]
     * lt      i     gt
     * <p>
     * After:
     * [ < v ] [    = v    ] [ > v]
     * lo      lt         gt     hi
     *
     * @param a
     */
    public void sort(T[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(T[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        // 3-way partition
        int lt = lo;
        int gt = hi;
        T v = a[lo];
        int i = lo;
        int cmp;
        while (i <= gt) {
            cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
