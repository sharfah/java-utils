package com.sharfah.util.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A set of stacks. A new stack is created when the current one
 * exceeds some threshold.
 * <p>
 * Imagine a stack of dinner plates. If the stack gets too high
 * it might topple, therefore we need to create a new stack.
 * <p>
 * Has the same functionality as a normal stack, but it also implements
 * a <tt>popAt</tt> method, which pops the stack at a specific index
 * and then reorders the stack so that each stack is back at full
 * capacity.
 *
 * @author fahd
 * @param <E>
 */
public class SetOfStacks<E> extends Stack<E> {

  private List<Stack<E>> stacks;
  private final int maxStackHeight;
  private int lastStackHeight;

  /**
   * Creates a set of stacks with the specified max height.
   * @param maxStackHeight the maximum height that a stack can have
   */
  public SetOfStacks(final int maxStackHeight) {
    this.maxStackHeight = maxStackHeight;
    lastStackHeight = 0;
    stacks = new ArrayList<>();
    stacks.add(new Stack<>()); //always keep a stack. Makes it easier
  }

  @Override
  public void push(final E e) {
    if (lastStackHeight < maxStackHeight) {
      getLastStack().push(e);
    } else {
      createNewStack().push(e);
    }
    lastStackHeight++;
  }

  @Override
  public E pop() {
    final E top = getLastStack().pop();
    if (getLastStack().isEmpty()) {
      if (stacks.size() > 1) {
        stacks.remove(stacks.size() - 1);
        lastStackHeight = maxStackHeight;
      }
      else {
        lastStackHeight = 0;
      }
    } else {
      lastStackHeight--;
    }
    return top;
  }

  /**
   * Pops the elements from the stack at the specified index.
   *
   * @param index of the stack to remove from
   * @return the top of the stack at the specified index
   * @throws NoSuchElementException if there is no stack at the specified index
   */
  public E popAt(final int index) {
    if (index < 0 || index >= stacks.size()) {
      throw new NoSuchElementException("No stack at index: " + index);
    }

    final E top = stacks.get(index).pop();

    // create a temp stack with all the elements
    // from the stacks ahead of this index
    final Stack<E> tmp = new Stack<>();
    for (int i = stacks.size() - 1; i > index; i--) {
      final Stack<E> stack = stacks.get(i);
      while (!stack.isEmpty()) {
        tmp.push(stack.pop());
      }
    }

    // remove all stacks ahead of this one
    stacks = stacks.subList(0, index + 1);

    // repopulate
    while (!tmp.isEmpty()) {
      push(tmp.pop());
    }

    return top;
  }

  @Override
  public E peek() {
    return getLastStack().peek();
  }

  /**
   * Returns the top of the stack at the specified index.
   * @param index
   * @return the element at the top of the stack
   * @throws NoSuchElementException if there is no stack at the specified index
   */
  public E peekAt(final int index) {
    if (index < 0 || index >= stacks.size()) {
      throw new NoSuchElementException("No stack at index: " + index);
    }
    return stacks.get(index).peek();
  }

  @Override
  public boolean isEmpty() {
    return getLastStack().isEmpty();
  }

  /**
   * Creates a new stack and adds it to the list.
   * @return the stack that was created
   */
  private Stack<E> createNewStack() {
    final Stack<E> stack = new Stack<>();
    stacks.add(stack);
    lastStackHeight = 0;
    return stack;
  }

  /**
   * Returns the last stack.
   * @return the last stack
   */
  private Stack<E> getLastStack() {
    return stacks.get(stacks.size() - 1);
  }
}
