package com.sharfah.util.algorithms;

import java.math.BigInteger;
import java.util.stream.IntStream;

/**
 * Factorial algorithms.
 *
 * @author fahd
 *
 */
public class Factorial {

  /**
   * Recursive approach.
   *
   * @param n
   * @return factorial of n
   */
  public static int factorial(final int n) {
    return n <= 1 ? 1 : n * factorial(n - 1);
  }

  /**
   * Tail recursive algorithm. The recursive call is the last thing that happens
   * in the function.
   * <p>
   * Tail-recursive functions can be optimised by the compiler in some
   * programming languages. Instead of storing each intermediate result of the
   * recursion onto different stack frames, a single stack frame can be reused
   * instead, because there's no need to keep track of intermediate results.
   * Unfortunately, Java does not support this kind of optimisation, but you
   * might want to take this approach anyway, in case the compiler is optimised
   * in the future. Other JVM languages (e.g. Scala) can optimise
   * tail-recursion.
   *
   * @param n
   * @return factorial of n
   */
  public static int tailRecursiveFactorial(final int n) {
    return tailRecursiveHelper(n, 1);
  }

  /**
   * Helper function used for tail recursive factorial.
   *
   * @param n
   * @param accumulator
   *          stores intermediate results
   * @return factorial of n
   */
  private static int tailRecursiveHelper(final int n, final int accumulator) {
    return n <= 1 ? accumulator : tailRecursiveHelper(n - 1, n * accumulator);
  }

  /**
   * Iterative approach.
   *
   * @param n
   * @return factorial of n
   */
  public static int iterativeFactorial(final int n) {
    if (n <= 1) {
      return 1;
    }
    int result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * Iterative approach using BigInteger.
   *
   * @param n
   * @return factorial of n
   */
  public static BigInteger bigFactorial(final int n) {
    if (n <= 1) {
      return BigInteger.ONE;
    }
    BigInteger result = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
      result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
  }

  /**
   * Factorial using Java 8 streams.
   *
   * @param n
   * @return factorial of n
   */
  public static int streamFactorial(final int n) {
    if (n <= 1) {
      return 1;
    }
    return IntStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
  }

}
