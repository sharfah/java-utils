package com.sharfah.util.collections;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class StackTest {

  @Test
  public void testPush() {
    final Stack<String> stack = new Stack<>();
    assertTrue(stack.isEmpty());
    stack.push("foo");
    assertThat(stack.peek(), is("foo"));
    stack.push("bar");
    assertThat(stack.peek(), is("bar"));
    assertFalse(stack.isEmpty());
  }

  @Test
  public void testPop() {
    final Stack<String> stack = new Stack<>();
    stack.push("foo");
    stack.push("bar");
    assertThat(stack.peek(), is("bar"));
    assertThat(stack.pop(), is("bar"));
    assertThat(stack.peek(), is("foo"));
    assertThat(stack.pop(), is("foo"));
  }

  @Test
  public void testPopOnEmptyStack() {
    assertThrows(NoSuchElementException.class, () -> {
      new Stack<>().pop();
    });
  }

  @Test
  public void testPeek() {
    final Stack<String> stack = new Stack<>();
    stack.push("foo");
    assertThat(stack.peek(), is("foo"));
    stack.push("bar");
    assertThat(stack.peek(), is("bar"));
  }

  @Test
  public void testPeekOnEmptyStack() {
    assertThrows(NoSuchElementException.class, () -> {
      new Stack<>().peek();
    });
  }

  @Test
  public void testIsEmpty() {
    final Stack<String> stack = new Stack<>();
    assertTrue(stack.isEmpty());
    stack.push("foo");
    assertFalse(stack.isEmpty());
    stack.pop();
    assertTrue(stack.isEmpty());
  }

  @Test
  public void testToString() {
    final Stack<String> stack = new Stack<>();
    assertThat(stack.toString(), is("[]"));
    stack.push("foo");
    assertThat(stack.toString(), is("[foo]"));
    stack.push("bar");
    assertThat(stack.toString(), is("[bar, foo]"));
    stack.pop();
    assertThat(stack.toString(), is("[foo]"));
  }
}
