package com.sharfah.util.algorithms;

import static com.sharfah.util.algorithms.Fibonacci.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class FibonacciTest {

  @Test
  public void testRecursive() {
    assertThat(fibonacci(0), is(0));
    assertThat(fibonacci(1), is(1));
    assertThat(fibonacci(6), is(8));
  }

  @Test
  public void testRecursiveMemoized() {
    assertThat(fibonacciMemoized(0), is(0));
    assertThat(fibonacciMemoized(1), is(1));
    assertThat(fibonacciMemoized(6), is(8));
  }
  @Test
  public void testIterative() {
    assertThat(iterativeFibonacci(0), is(0));
    assertThat(iterativeFibonacci(1), is(1));
    assertThat(iterativeFibonacci(6), is(8));
  }
}
