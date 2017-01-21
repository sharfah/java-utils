package com.sharfah.util.collections;

public class Stacks {

  // Suppresses default constructor, ensuring non-instantiability.
  private Stacks() {
  }

  /**
   * Returns the minimum element of the given stack.
   * All elements must implement the <tt>Comparable</tt> interface.
   * <p>
   * This method iterates over the entire stack, hence it is O(n).
   *
   * @param <T> the type of the element
   * @param stack the stack whose minimum element is to be found
   * @return the minimum element
   * @throws NoSuchElementException if the stack is empty
   */
  public static <T extends Comparable<T>> T min(final Stack<T> stack) {
    T min = stack.peek();
    final Stack<T> temp = new Stack<>();
    while (!stack.isEmpty()) {
      final T top = stack.pop();
      if (top.compareTo(min) < 0) {
        min = top;
      }
      temp.push(top);
    }
    move(temp, stack);
    return min;
  }

  /**
   * Moves the elements from one stack to another.
   * Iterates over all elements in the source stack
   * and pushes them into the destination, leaving the source empty.
   *
   * @param <T> the type of the element
   * @param src The source stack to move elements from
   * @param dest The destination stack to add elements to
   */
  public static <T> void move(final Stack<T> src, final Stack<T> dest) {
    while (!src.isEmpty()) {
      dest.push(src.pop());
    }
  }

  /**
   * Sorts a stack such that the smallest elements are on top.
   * Uses an additional temporary stack.
   * This algorithm is O(N^2) time and O(N) space.
   * <p>
   * We could also implement a modified mergesort or quicksort.
   * Divide the stack into two parts and create two extra stacks.
   * Sort each one recursively and then merge them back
   * together in sorted order into the original stack. This would
   * require 2 extra stacks per level of recursion.
   *
   * @param <T>
   * @param stack the stack to sort
   */
  public static <T extends Comparable<T>> void sort(final Stack<T> stack) {
    final Stack<T> tmp = new Stack<>();
    while (!stack.isEmpty()) {
      final T top = stack.pop();
      while (!tmp.isEmpty() && top.compareTo(tmp.peek()) < 0) {
        stack.push(tmp.pop());
      }
      tmp.push(top);
    }
    while (!tmp.isEmpty()) {
      stack.push(tmp.pop());
    }
  }
}
