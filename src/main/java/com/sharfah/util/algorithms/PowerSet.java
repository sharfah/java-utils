package com.sharfah.util.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The power set of a set S is the set of all subsets of S including the empty
 * set and S itself.
 * <p>
 * For example, the power set of {1, 2, 3} is {{}, {1}, {2}, {3}, {1, 2}, {1,
 * 3}, {2, 3}, {1, 2, 3}}.
 * <p>
 * The problem can be solved using recursion. We know that if the initial set is
 * empty, then its power set is {{}} and if it has only one element {a} then its
 * power set is {{}, {a}}.
 * <p>
 * How many subsets of a set are there? Each element can either be in the set or
 * out, so there are 2^n subsets.
 *
 * @author fahd
 *
 */
public class PowerSet {

  /**
   * Returns the power set of the specified set.
   * <p>
   * Complexity: O(n2^n). There are 2^n subsets and each of the n elements will
   * be contained in half of the subsets. Total number of elements across all of
   * the subsets is n * 2^(n-1).
   *
   * @param <E>
   * @param list
   * @return the power set
   */
  public static <E> List<List<E>> powerSet(final List<E> list) {
    if (list.isEmpty()) {
      return Arrays.asList(Collections.emptyList());
    }

    final List<List<E>> result = new ArrayList<>();
    final E first = list.get(0);
    final List<E> rest = list.subList(1, list.size());
    final List<List<E>> powerSet = powerSet(rest);
    result.addAll(powerSet);
    for (final List<E> subSet : powerSet) {
      final ArrayList<E> newSubSet = new ArrayList<>(subSet);
      newSubSet.add(0, first);
      result.add(newSubSet);
    }
    return result;
  }

  /**
   * Every element can either be in a set or out.
   * Therefore, each set can be represented as a binary number.
   * <p>
   * For example, given a set {a,b,c}, the subset 101 would mean {a,c}.
   * <p>
   * There are 2^n possible subsets. We can generate all binary numbers (i.e. integers)
   * and translate each binary number into a set.
   * @param <E>
   * @param list
   * @return the power set
   */
  public static <E> List<List<E>> iterativePowerSet(final List<E> list) {
    final int numSubSets = 1 << list.size();
    final List<List<E>> result = new ArrayList<>();
    for (int i = 0; i < numSubSets; i++) {
      final List<E> subSet = new ArrayList<>();
      int index = 0;
      for (int j = i; j > 0; j >>= 1) { // keep shifting right
        if ((j & 1) == 1) { // check last bit
          subSet.add(list.get(index));
        }
        index++;
      }
      result.add(subSet);
    }
    return result;
  }

  /**
   * Alternative, (slower) version of iterativePowerSet, but
   * might be easier to understand.
   * @param <E>
   * @param list
   * @return the power set
   */
  public static <E> List<List<E>> iterativePowerSet2(final List<E> list) {
    final int numSubSets = 1 << list.size(); // 2^n
    final List<List<E>> result = new ArrayList<>();
    for (int i = 0; i < numSubSets; i++) {
      final List<E> set = new ArrayList<>();
      for (int j = 0; j < list.size(); j++) {
        if ((i & 1 << j) != 0) { // is jth bit set?
          set.add(list.get(j));
        }
      }
      result.add(set);
    }
    return result;
  }
}
