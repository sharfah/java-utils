package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class StacksTest {

  @Test
  public void testMin() {
    final Stack<String> stack = new Stack<>();
    stack.push("bob");
    stack.push("alice");
    stack.push("charlie");
    assertThat(Stacks.min(stack), is("alice"));
    assertThat(stack.pop(), is("charlie"));
    assertThat(stack.pop(), is("alice"));
    assertThat(stack.pop(), is("bob"));
    assertThat(stack.isEmpty(), is(true));
  }

  @Test (expected = NoSuchElementException.class)
  public void testMinThrowsExceptionOnEmptyStack() {
    final Stack<String> stack = new Stack<>();
    Stacks.min(stack);
  }

  @Test
  public void testMove() {
    final Stack<String> stack = new Stack<>();
    stack.push("bob");
    stack.push("alice");
    stack.push("charlie");

    final Stack<String> stack2 = new Stack<>();
    stack2.push("foo");

    Stacks.move(stack, stack2);

    assertThat(stack.isEmpty(), is(true));
    assertThat(stack2.pop(), is("bob"));
    assertThat(stack2.pop(), is("alice"));
    assertThat(stack2.pop(), is("charlie"));
    assertThat(stack2.pop(), is("foo"));
  }

  @Test
  public void testSort() {
    final Stack<Integer> stack = new Stack<>();
    stack.push(10);
    stack.push(3);
    stack.push(1);
    stack.push(5);
    stack.push(4);
    stack.push(2);
    Stacks.sort(stack);
    assertThat(stack.pop(), is(1));
    assertThat(stack.pop(), is(2));
    assertThat(stack.pop(), is(3));
    assertThat(stack.pop(), is(4));
    assertThat(stack.pop(), is(5));
    assertThat(stack.pop(), is(10));
  }
}
