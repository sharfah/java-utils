package com.sharfah.util.algorithms;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SortingTest {

  @Test
  public void testBubbleSort() {
    final Integer[] arr = new Integer[] { 5, 1, 8, 2, 3, 6 };
    Sorting.bubbleSort(arr);
    assertThat(arr, is(new Integer[] { 1, 2, 3, 5, 6, 8 }));

    final Integer[] singleArr = new Integer[] { 5 };
    Sorting.bubbleSort(singleArr);
    assertThat(singleArr, is(new Integer[] { 5 }));
  }

  @Test
  public void testBubbleSortOptimised() {
    final Integer[] arr = new Integer[] { 5, 1, 8, 2, 3, 6 };
    Sorting.bubbleSortOptimised(arr);
    assertThat(arr, is(new Integer[] { 1, 2, 3, 5, 6, 8 }));

    final Integer[] singleArr = new Integer[] { 5 };
    Sorting.bubbleSortOptimised(singleArr);
    assertThat(singleArr, is(new Integer[] { 5 }));
  }

  @Test
  public void testSelectionSort() {
    final Integer[] arr = new Integer[] { 5, 1, 8, 2, 3, 6 };
    Sorting.selectionSort(arr);
    assertThat(arr, is(new Integer[] { 1, 2, 3, 5, 6, 8 }));

    final Integer[] singleArr = new Integer[] { 5 };
    Sorting.selectionSort(singleArr);
    assertThat(singleArr, is(new Integer[] { 5 }));
  }

  @Test
  public void testInsertionSort() {
    final Integer[] arr = new Integer[] { 3, 7, 4, 9, 5, 2, 6, 1 };
    Sorting.insertionSort(arr);
    assertThat(arr, is(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 9 }));

    final Integer[] singleArr = new Integer[] { 5 };
    Sorting.insertionSort(singleArr);
    assertThat(singleArr, is(new Integer[] { 5 }));
  }

  @Test
  public void testMergeSort() {
    final Integer[] arr = new Integer[] { 3, 7, 4, 9, 5, 2, 6, 1 };
    Sorting.mergeSort(arr);
    assertThat(arr, is(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 9 }));

    final Integer[] arrOddLength = new Integer[] { 3, 7, 4, 9, 5, 2, 6, 1, 8 };
    Sorting.mergeSort(arrOddLength);
    assertThat(arrOddLength, is(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));

    final Integer[] singleArr = new Integer[] { 5 };
    Sorting.mergeSort(singleArr);
    assertThat(singleArr, is(new Integer[] { 5 }));
  }

  @Test
  public void testQuickSort() {
    final Integer[] arr = new Integer[] { 3, 7, 4, 9, 5, 2, 6, 1 };
    Sorting.quickSort(arr);
    assertThat(arr, is(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 9 }));

    final Integer[] arrOddLength = new Integer[] { 3, 7, 4, 9, 5, 2, 6, 1, 8 };
    Sorting.quickSort(arrOddLength);
    assertThat(arrOddLength, is(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));

    final Integer[] singleArr = new Integer[] { 5 };
    Sorting.quickSort(singleArr);
    assertThat(singleArr, is(new Integer[] { 5 }));

    final Integer[] repeatedArr = new Integer[] { 5, 5, 5 };
    Sorting.quickSort(repeatedArr);
    assertThat(repeatedArr, is(new Integer[] { 5, 5, 5 }));
  }
}
