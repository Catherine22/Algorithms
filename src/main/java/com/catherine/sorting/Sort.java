package com.catherine.sorting;

/**
 * @author : Catherine
 * @param <T>
 */
public abstract class Sort<T extends Comparable<? super T>> {

    public abstract T[] sort(T[] array);

    public abstract T[] sort(T[] array, boolean isAscending);
}
