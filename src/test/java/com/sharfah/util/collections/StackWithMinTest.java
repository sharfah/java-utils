package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StackWithMinTest {

  @Test
  public void test() {
    final StackWithMin<String> stack = new StackWithMin<>();
    stack.push("bob");
    assertThat(stack.min(), is("bob"));
    stack.push("alice");
    assertThat(stack.min(), is("alice"));
    stack.push("charlie");
    assertThat(stack.min(), is("alice"));
    stack.push("adam");
    assertThat(stack.min(), is("adam"));
    stack.pop();
    assertThat(stack.min(), is("alice"));
    assertThat(stack.peek(), is("charlie"));
  }
}
