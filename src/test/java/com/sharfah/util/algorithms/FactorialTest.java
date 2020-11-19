package com.sharfah.util.algorithms;

import static com.sharfah.util.algorithms.Factorial.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class FactorialTest {

  @Test
  public void testRecursive() {
    assertThat(factorial(5), is(120));
    assertThat(factorial(0), is(1));
  }

  @Test
  public void testTailRecursive() {
    assertThat(tailRecursiveFactorial(5), is(120));
    assertThat(tailRecursiveFactorial(0), is(1));
  }

  @Test
  public void testIterative() {
    assertThat(iterativeFactorial(5), is(120));
    assertThat(iterativeFactorial(0), is(1));
  }

  @Test
  public void testBigInteger() {
    assertThat(bigFactorial(5), is(BigInteger.valueOf(120)));
    assertThat(bigFactorial(0), is(BigInteger.ONE));
  }

  @Test
  public void testStream() {
    assertThat(streamFactorial(5), is(120));
    assertThat(streamFactorial(0), is(1));
  }

}
