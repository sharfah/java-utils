package com.sharfah.util.collections;
import java.util.NoSuchElementException;

/**
 * A Queue implements FIFO (first-in first-out) ordering.
 * Items are removed in the same order that they are added.
 *
 * @author fahd
 * @param <E>
 */
public class Queue<E> {

  /**
   * An item in the queue.
   * @param <E>
   */
  private static class QueueItem<E> {
    final E data;
    QueueItem<E> next;

    public QueueItem(final E data) {
      this.data = data;
    }
  }

  private QueueItem<E> first;
  private QueueItem<E> last;

  /**
   * Returns true if the queue is empty.
   * @return true if the queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Add an item to the end of the queue.
   * @param e the item to add
   */
  public void add(final E e) {
    final QueueItem<E> toAdd = new QueueItem<>(e);
    if (last != null) {
      last.next = toAdd;
    }
    last = toAdd;
    if (first == null) {
      first = last;
    }
  }

  /**
   * Removes the first item in the queue.
   * @return the first item
   */
  public E remove() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    final E data = first.data;
    first = first.next;
    if (first == null) {
      last = null;
    }
    return data;
  }

  /**
   * Return the first item in the queue.
   * @return the first item
   */
  public E peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return first.data;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder('[');
    sb.append('[');
    QueueItem<E> item = first;
    while (item != null) {
      sb.append(item.data);
      if (item.next != null) {
        sb.append(',').append(' ');
      }
      item = item.next;
    }
    sb.append(']');
    return sb.toString();
  }
}
