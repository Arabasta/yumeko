package com.keiyam.spring_backend.util;

import java.util.List;

/**
 * Utility class for list operations.
 */
public final class ListUtil {

    private ListUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * Checks if the given list is sorted in ascending order.
     *
     * @param list the list to check
     * @param <T> the type of elements in the list
     * @return true if the list is sorted in ascending order, false otherwise
     */
    public static <T extends Comparable<T>> boolean isSortedAscending(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given list is sorted in descending order.
     *
     * @param list the list to check
     * @param <T> the type of elements in the list
     * @return true if the list is sorted in descending order, false otherwise
     */
    public static <T extends Comparable<T>> boolean isSortedDescending(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) < 0) {
                return false;
            }
        }
        return true;
    }
}
