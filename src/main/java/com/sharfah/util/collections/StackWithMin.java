package com.sharfah.util.collections;

/**
 * A stack with an additional method to return the minimum in O(1) time.
 * It maintains a "history" of minimums in another internal stack.
 * All elements must implement the <tt>Comparable</tt> interface.
 *
 * @author fahd
 * @param <E>
 */
public class StackWithMin<E extends Comparable<E>> extends Stack<E> {

  private final Stack<E> mins;

  public StackWithMin() {
    mins = new Stack<>();
  }

  @Override
  public void push(final E e) {
    super.push(e);
    if (mins.isEmpty()) {
      mins.push(e);
    } else if (e.compareTo(min()) < 0) {
      mins.push(e);
    }
  }

  @Override
  public E pop() {
    final E top = super.pop();
    if (top == mins.peek()) {
      mins.pop();
    }
    return top;
  }

  /**
   * Returns the minimum of the stack.
   * @return the minimum element
   * @throws NoSuchElementException if the stack is empty
   */
  public E min() {
    return mins.peek();
  }
}
