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

  /**
   * Quick Sort.
   * <p>
   * The steps are:
   * <ol>
   * <li>Pick an element, called a pivot, from the array.
   * <li>Move elements < pivot before the pivot and those > pivot, after it
   * <li>Repeat recursively to the sub-array less than the pivot and to the sub-array after the pivot
   * </ol>
   * <li>Runtime (average and best): O(n log n)
   * <li>Worst runtime: O(n^2)
   * <li>Memory: O(log n)
   * @param <E>
   * @param arr the array to sort
   */
  public static <E extends Comparable<E>> void quickSort(final E[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  private static <E extends Comparable<E>> void quickSort(final E[] arr, final int start, final int end) {
    final int p = partition(arr, start, end);
    if (start < p - 1) { // sort left half
      quickSort(arr, start, p - 1);
    }
    if (p < end) { // sort right half
      quickSort(arr, p, end);
    }
  }

  /**
   * Partitioning: reorder the array so that all elements with values less than
   * the pivot come before the pivot, while all elements with values greater
   * than the pivot come after it (equal values can go either way). After this
   * partitioning, the pivot is in its final position.
   * <p>
   * Hoare partition scheme: Uses two indices that start at the ends of the array being partitioned,
   * then move toward each other, until they detect an inversion. Swap the inverted elements.
   * @param <E>
   * @param arr
   * @param start
   * @param end
   * @return the pivot position
   */
  private static <E extends Comparable<E>> int partition(final E[] arr, int start, int end) {
    // Pick the pivot:
    // Worst case is if the pivot is the smallest or largest element in the array,
    // which will lead to one partition having size 0, giving O(n^2) runtime.
    // It's better to pick a random index, or the middle index, of the median of the first, middle and last elements.
    final E pivot = arr[start + (end - start) / 2]; // could do (start + end)/2 but that could cause integer overflow

    while (start <= end) {
      // find element on the left that should be on the right
      while (arr[start].compareTo(pivot) < 0) {
        start++;
      }

      // find element on the right that should be on the left
      while (arr[end].compareTo(pivot) > 0) {
        end--;
      }

      // swap the elements
      if (start <= end) {
        final E tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
        start++;
        end--;
      }
    }
    return start;
  }
}
