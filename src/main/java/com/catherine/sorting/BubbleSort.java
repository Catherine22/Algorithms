package com.catherine.sorting;

import java.util.Arrays;


/**
 * best: n.
 * worst: n^2
 * avg.: n^2
 * stability: stable <br>
 * memory space: 1
 *
 * @param <T>
 * @author : Catherine
 */
public class BubbleSort<T extends Comparable<? super T>> extends Sort<T> {

    @Override
    public T[] sort(T[] array) {
        return sort(array, true);
    }

    @Override
    public T[] sort(T[] array, boolean isAscending) {
        T[] sortedArray = Arrays.copyOf(array, array.length);
        T n1, n2, temp;
        for (int i = sortedArray.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                n1 = sortedArray[j];
                n2 = sortedArray[j + 1];
                if (isAscending) {
                    if (n1.compareTo(n2) > 0) {
                        temp = n1;
                        sortedArray[j] = n2;
                        sortedArray[j + 1] = temp;
                    }
                } else {
                    if (n1.compareTo(n2) < 0) {
                        temp = n1;
                        sortedArray[j] = n2;
                        sortedArray[j + 1] = temp;
                    }
                }
            }
        }
        return sortedArray;
    }

    /**
     * 改良版，排序时，若从第一个元素到正在排的元素前一位都已经排好时，可以直接终止排序。
     * 原本扫描路径呈现三角形，改良后若符合条件会变成平行四边形。
     * E.g. [1, 2, 3, 4], 25, 14, 26
     *
     * @param array
     * @param isAscending
     * @return
     */
    public T[] sort1(T[] array, boolean isAscending) {
        T[] sortedArray = Arrays.copyOf(array, array.length);
        T n1, n2, temp;
        int exchanges = 0;
        int len = sortedArray.length - 1;

        while (len >= 0) {
            for (int i = 0; i < len; i++) {
                n1 = sortedArray[i];
                n2 = sortedArray[i + 1];

                if (isAscending) {
                    if (n1.compareTo(n2) > 0) {
                        temp = n1;
                        sortedArray[i] = n2;
                        sortedArray[i + 1] = temp;
                        exchanges++;
                    }
                } else {
                    if (n1.compareTo(n2) < 0) {
                        temp = n1;
                        sortedArray[i] = n2;
                        sortedArray[i + 1] = temp;
                        exchanges++;
                    }
                }
            }

            if (exchanges == 0) {
                break;
            }
            exchanges = 0;
            len--;
        }
        return sortedArray;
    }

    /**
     * 改良版，排序时，若从某一点到最后一位都已经排好时，表示该部分可掠过不检查，直接把排序范围改成某一点到前一位，大幅减少搜寻范围。
     * 原本扫描路径呈现三角形，改良后若符合条件会变成更小的三角形。
     * E.g. 9, 2, 6, [11, 12, 13, 14]
     *
     * @param array
     * @param isAscending
     * @return
     */
    public T[] sort2(T[] array, boolean isAscending) {
        T[] sortedArray = Arrays.copyOf(array, array.length);
        T n1, n2, temp;
        int end = sortedArray.length - 1;
        int lastModified = -1;
        while (end >= 0) {
            for (int j = 0; j < end; j++) {
                n1 = sortedArray[j];
                n2 = sortedArray[j + 1];
                if (isAscending) {
                    if (n1.compareTo(n2) > 0) {
                        temp = n1;
                        sortedArray[j] = n2;
                        sortedArray[j + 1] = temp;
                        lastModified = j;
                    }
                } else {
                    if (n1.compareTo(n2) < 0) {
                        temp = n1;
                        sortedArray[j] = n2;
                        sortedArray[j + 1] = temp;
                        lastModified = j;
                    }
                }
            }
            if (lastModified < 0) {
                break;
            }

            end = lastModified + 1;
            lastModified = -1;
        }
        return sortedArray;
    }
}
