package com.sharfah.util.algorithms;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Parentheses {

  /**
   * Generate a list of all valid combinations of n pairs of parentheses.
   * For example, if n is 3:
   * ((())), (()()), ()(()), (())(), ()()()
   *
   * @param n
   * @return a set of parentheses combinations
   */
  public static Set<String> generate(final int n) {

    if (n == 1) {
      return Collections.singleton("()");
    }

    final Set<String> result = new HashSet<>();
    final Set<String> prev = generate(n - 1);

    for (final String s : prev) {
      for (int i = 0; i < s.length(); i++) {
        final StringBuilder sb = new StringBuilder(s);
        sb.insert(i, "()");
        result.add(sb.toString());
      }
    }
    return result;
  }
}
