package com.sharfah.util.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generate permutations.
 *
 * @author fahd
 */
public class Permutation {

  /**
   * Generates permutations of a string of unique characters.
   * Example: abc => abc, acb, bac, bca, cab, cba
   * @param s
   * @return permutations
   */
  public static Set<String> permutations(final String s) {
    if (s.length() == 1) {
      return Collections.singleton(s);
    }

    final char first = s.charAt(0);
    final String rest = s.substring(1);
    final Set<String> perms = permutations(rest);
    final Set<String> result = new HashSet<>();
    for (final String p : perms) {
      for (int i = 0; i <= p.length(); i++) {
        final StringBuilder sb = new StringBuilder(p);
        sb.insert(i, first);
        result.add(sb.toString());
      }
    }
    return result;
  }

  /**
   * Generates permutations of a list of unique elements.
   * @param <E>
   * @param list
   * @return permutations
   */
  public static <E> List<List<E>> permutations(final List<E> list) {
    if (list.size() == 1) {
      return Collections.singletonList(list);
    }

    final E first = list.get(0);
    final List<E> rest = list.subList(1, list.size());
    final List<List<E>> perms = permutations(rest);
    final List<List<E>> result = new ArrayList<>();
    for (final List<E> p : perms) {
      for (int i = 0; i <= p.size(); i++) {
        final List<E> newList = new ArrayList<>(p);
        newList.add(i, first);
        result.add(newList);
      }
    }
    return result;
  }

}
