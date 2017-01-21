package com.sharfah.util.collections;

/**
 * A queue implemented using two stacks. One stack has the newest items on top
 * and the other has the oldest items on top.
 *
 * @author fahd
 * @param <E>
 */
public class QueueWithStacks<E> {

  private final Stack<E> newestOnTop;
  private final Stack<E> oldestOnTop;

  /**
   * Creates the queue with two stacks;
   */
  public QueueWithStacks() {
    newestOnTop = new Stack<>();
    oldestOnTop = new Stack<>();
  }

  /**
   * Adds the element to the queue.
   *
   * @param e the element to add
   */
  public void add(final E e) {
    newestOnTop.push(e);
  }

  /**
   * Removes the element from the front of the queue.
   *
   * @return the element
   * @throws NoSuchElementException if the queue is empty
   */
  public E remove() {
    fillOldestOnTop();
    return oldestOnTop.pop();
  }

  /**
   * Returns the element from the front of the queue.
   *
   * @return the element
   * @throws NoSuchElementException if the queue is empty
   */
  public E peek() {
    fillOldestOnTop();
    return oldestOnTop.peek();
  }

  /**
   * Tests if the queue is empty
   *
   * @return true if empty, false otherwise
   */
  public boolean isEmpty() {
    return oldestOnTop.isEmpty() && newestOnTop.isEmpty();
  }

  /**
   * Moves elements from one stack to the other, if necessary.
   */
  private void fillOldestOnTop() {
    if (oldestOnTop.isEmpty()) {
      while (!newestOnTop.isEmpty()) {
        oldestOnTop.push(newestOnTop.pop());
      }
    }
  }
}
