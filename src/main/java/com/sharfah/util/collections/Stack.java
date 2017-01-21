package com.sharfah.util.collections;

import java.util.NoSuchElementException;

/**
 * A stack uses LIFO (last-in first-out) ordering.
 * The most recent item added to the stack is the first item to be removed.
 * Think of a stack of dinner plates.
 * <p>
 * A stack has constant time adds and removes, but does not allow constant
 * time access to the nth item.
 *
 * @author fahd
 * @param <E>
 */
public class Stack<E> {

  /**
   * A stack item.
   * @param <E>
   */
  private static class StackItem<E> {
    final E data;
    StackItem<E> next;

    public StackItem(final E data) {
      this.data = data;
    }
  }

  private StackItem<E> top;

  /**
   * Add an item to the top of the stack.
   * @param e the item to add
   */
  public void push(final E e) {
    final StackItem<E> toAdd = new StackItem<>(e);
    toAdd.next = top;
    top = toAdd;
  }

  /**
   * Remove the top item from the stack.
   * @return the top of the stack
   * @throws NoSuchElementException if the stack is empty
   */
  public E pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack is empty");
    }
    final E data = top.data;
    top = top.next;
    return data;
  }

  /**
   * Return the top of the stack but do not remove it.
   * @return the top of the stack
   * @throws NoSuchElementException if the stack is empty
   */
  public E peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack is empty");
    }
    return top.data;
  }

  /**
   * Return true if the stack is empty.
   * @return true if the stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return top == null;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append('[');
    StackItem<E> item = top;
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
