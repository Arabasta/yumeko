package com.keiyam.spring_backend.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListUtilTest {

    @Test
    void testIsSortedAscending_EmptyList() {
        List<Integer> emptyList = Collections.emptyList();
        assertTrue(ListUtil.isSortedAscending(emptyList));
    }

    @Test
    void testIsSortedAscending_SingleElementList() {
        List<Integer> singleElementList = Collections.singletonList(1);
        assertTrue(ListUtil.isSortedAscending(singleElementList));
    }

    @Test
    void testIsSortedAscending_SortedList() {
        List<Integer> sortedList = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(ListUtil.isSortedAscending(sortedList));
    }

    @Test
    void testIsSortedAscending_UnsortedList() {
        List<Integer> unsortedList = Arrays.asList(5, 4, 3, 2, 1);
        assertFalse(ListUtil.isSortedAscending(unsortedList));
    }

    @Test
    void testIsSortedAscending_ListWithDuplicates() {
        List<Integer> listWithDuplicates = Arrays.asList(1, 2, 2, 3, 4);
        assertTrue(ListUtil.isSortedAscending(listWithDuplicates));
    }

    @Test
    void testIsSortedAscending_ListWithNegativeNumbers() {
        List<Integer> listWithNegativeNumbers = Arrays.asList(-3, -2, -1, 0, 1);
        assertTrue(ListUtil.isSortedAscending(listWithNegativeNumbers));
    }

    @Test
    void testIsSortedAscending_UnsortedListWithNegativeNumbers() {
        List<Integer> unsortedListWithNegativeNumbers = Arrays.asList(3, -2, -1, 0, 1);
        assertFalse(ListUtil.isSortedAscending(unsortedListWithNegativeNumbers));
    }
}
