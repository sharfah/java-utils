package com.sharfah.util.collections;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class SetOfStacksTest {

  @Test
  public void testPush() {
    final SetOfStacks<String> stack = new SetOfStacks<>(3);
    assertThat(stack.isEmpty(), is(true));
    stack.push("foo");
    assertThat(stack.peek(), is("foo"));
    stack.push("bar");
    assertThat(stack.peek(), is("bar"));
    stack.push("baz");
    assertThat(stack.peek(), is("baz"));
    stack.push("one");
    assertThat(stack.peek(), is("one"));
    stack.push("two");
    assertThat(stack.peek(), is("two"));
  }

  @Test
  public void testPop() {
    final SetOfStacks<String> stack = new SetOfStacks<>(3);
    stack.push("foo");
    assertThat(stack.pop(), is("foo"));
    stack.push("foo");
    stack.push("bar");
    stack.push("baz");
    stack.push("one");
    stack.push("two");
    assertThat(stack.pop(), is("two"));
    assertThat(stack.pop(), is("one"));
    assertThat(stack.pop(), is("baz"));
    assertThat(stack.pop(), is("bar"));
    assertThat(stack.pop(), is("foo"));
    assertThat(stack.isEmpty(), is(true));
  }

  @Test
  public void testIsEmpty() {
    final SetOfStacks<String> stack = new SetOfStacks<>(3);
    assertThat(stack.isEmpty(), is(true));
    stack.push("foo");
    assertThat(stack.pop(), is("foo"));
    assertThat(stack.isEmpty(), is(true));
  }

  @Test
  public void testPopAt() {
    final SetOfStacks<String> stack = new SetOfStacks<>(3);
    stack.push("foo");
    stack.push("bar");
    stack.push("baz");
    stack.push("one");
    stack.push("two");

    assertThat(stack.popAt(0), is("baz"));

    assertThat(stack.pop(), is("two"));
    assertThat(stack.pop(), is("one"));
    assertThat(stack.pop(), is("bar"));
    assertThat(stack.pop(), is("foo"));
  }

  @Test
  public void testPeekAt() {
    final SetOfStacks<String> stack = new SetOfStacks<>(3);
    stack.push("foo");
    stack.push("bar");
    stack.push("baz");
    stack.push("one");
    stack.push("two");
    assertThat(stack.peekAt(1), is("two"));
    assertThat(stack.peekAt(0), is("baz"));
    assertThat(stack.popAt(0), is("baz"));
    assertThat(stack.peekAt(1), is("two"));
    assertThat(stack.peekAt(0), is("one"));
  }

}
