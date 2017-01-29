package com.sharfah.util.algorithms;

/**
 * Sorting algorithms.
 *
 * @author fahd
 */
public class Sorting {

  /**
   * Bubble Sort.
   * <p>
   * Start at the beginning of the array and swap the first two elements if the
   * first is greater than the second. Then go to the next pair and so on.
   * Continue sweeping the array until it is sorted. Largest items will bubble
   * to the end of the array.
   *
   * <li>Runtime (average and worst): O(n^2)
   * <li>Best runtime: O(n)
   * <li>Memory: O(1)
   *
   * @param <E>
   * @param arr the array to sort
   */
  public static <E extends Comparable<E>> void bubbleSort(final E[] arr) {
    boolean swapped = false;
    do {
      swapped = false;
      for (int i = 1; i < arr.length; i++) {
        if (arr[i - 1].compareTo(arr[i]) > 0) {
          final E tmp = arr[i];
          arr[i] = arr[i - 1];
          arr[i - 1] = tmp;
          swapped = true;
        }
      }
    } while (swapped);
  }

  /**
   * Optimised bubble sort.
   * All elements after the last swap are already sorted and do not need to be
   * checked again.
   * @param <E>
   * @param arr the array to sort
   */
  public static <E extends Comparable<E>> void bubbleSortOptimised(final E[] arr) {
    int n = arr.length;
    do {
      int lastSwapIndex = 0;
      for (int i = 1; i < n; i++) {
        if (arr[i - 1].compareTo(arr[i]) > 0) {
          final E tmp = arr[i];
          arr[i] = arr[i - 1];
          arr[i - 1] = tmp;
          lastSwapIndex = i;
        }
      }
      // elements after the last swap index are already sorted
      n = lastSwapIndex;
    } while (n != 0);
  }

  /**
   * Selection Sort.
   * <p>
   * Find the smallest element and move it to front (by swapping). Then find
   * the second smallest and move it again. Continue.
   * <li>Runtime (best, average and worst): O(n^2)
   * <li>Memory: O(1)
   * @param <E>
   * @param arr the array to sort
   */
  public static <E extends Comparable<E>> void selectionSort(final E[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j].compareTo(arr[minIndex]) < 0) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        // swap i and min
        final E tmp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = tmp;
      }
    }
  }

  /**
   * Insertion Sort.
   * <p>
   * Each iteration, insertion sort removes one element from the input array,
   * finds the location it belongs within the sorted list, and inserts it there.
   * <li>Runtime (average and worst): O(n^2)
   * <li>Best runtime: O(n)
   * <li>Memory: O(1)
   * @param <E>
   * @param arr the array to sort
   */
  public static <E extends Comparable<E>> void insertionSort(final E[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int j = i;
      while (j > 0 && arr[j].compareTo(arr[j - 1]) < 0) {
        final E tmp = arr[j];
        arr[j] = arr[j - 1];
        arr[j - 1] = tmp;
        j--;
      }
    }
  }

  /**
   * Merge Sort.
   * Divide the array in half, sort each one and then merge.
   * <li>Runtime (best, average and worst): O(n log n)
   * <li>Memory: O(n)
   * @param <E>
   * @param arr the array to sort
   */
  public static <E extends Comparable<E>> void mergeSort(final E[] arr) {
    mergeSort(arr, 0, arr.length, (E[])new Comparable[arr.length]);
  }

  private static <E extends Comparable<E>> void mergeSort(final E[] arr, final int start, final int end, final E[] helper) {
    if (end - start < 2) {
      return;
    }
    final int mid = (start + end) / 2;
    mergeSort(arr, start, mid, helper); // sort left
    mergeSort(arr, mid, end, helper); // sort right
    merge(arr, start, end, mid, helper); // merge
  }

  private static <E extends Comparable<E>> void merge(final E[] arr, final int start, final int end, final int mid,
                                                      final E[] helper) {
    System.arraycopy(arr, start, helper, start, end - start); // copy both halves into a helper array
    int i = start;
    int j = mid;
    int k = start;

    // compare elements from the left and right half and update the array
    while (i < mid && j < end) {
      if (helper[i].compareTo(helper[j]) < 0) {
        arr[k] = helper[i];
        i++;
      } else {
        arr[k] = helper[j];
        j++;
      }
      k++;
    }

    // copy the rest of the left array into the target array
    // the right half does not need to be copied because it is already there!
    while (i < mid) {
      arr[k++] = helper[i++];
    }
  }
}
