package com.sharfah.util.algorithms;

public class Fibonacci {

  /**
   * Calculate the nth fibonacci number recursively.
   * <p>
   * Runtime: The total number of nodes in the tree represents the runtime,
   * which is roughly O(2^n).
   *
   * @param n
   * @return the value of the nth fibonacci number
   */
  public static int fibonacci(final int n) {
    return n == 0 || n == 1 ? n : fibonacci(n - 1) + fibonacci(n - 2);
  }

  /**
   * Calculate the nth fibonacci number recursively with memoization.
   * <p>
   * Runtime: O(n).
   *
   * @param n
   * @return the value of the nth fibonacci number
   */
  public static int fibonacciMemoized(final int n) {
    return memoizedHelper(n, new int[n + 1]);
  }

  /**
   * Memoized fibonacci.
   *
   * @param n
   * @param memo a cache to store fibonacci numbers
   * @return
   */
  private static int memoizedHelper(final int n, final int[] memo) {
    if (n == 0 || n == 1) {
      return n;
    }
    if (memo[n] == 0) {
      memo[n] = memoizedHelper(n - 1, memo) + memoizedHelper(n - 2, memo);
    }
    return memo[n];
  }

  /**
   * Iterative fibonacci.
   *
   * @param n
   * @return the value of the nth fibonacci number
   */
  public static int iterativeFibonacci(final int n) {
    if (n == 0) {
      return 0;
    }

    int prev = 0;
    int curr = 1;

    for (int i = 1; i < n; i++) {
      final int next = prev + curr;
      prev = curr;
      curr = next;
    }
    return curr;
  }
}
