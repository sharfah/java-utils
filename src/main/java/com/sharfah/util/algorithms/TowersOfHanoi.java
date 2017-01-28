package com.sharfah.util.algorithms;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * Classic problem of the Towers of Hanoi.
 * You have 3 towers and N disks.
 * Tower 1 has all the disks in ascending order from top to bottom.
 * You have to move all the disks to the third tower, by moving one disk
 * at a time and not placing a larger disk on a smaller one.
 *
 * @author fahd
 */
public class TowersOfHanoi {

  private final Stack<Integer> tower1;
  private final Stack<Integer> tower2;
  private final Stack<Integer> tower3;

  public TowersOfHanoi(final Integer... ints) {
    tower1 = new Stack<>();
    Stream.of(ints).sorted(Collections.reverseOrder()).forEach(tower1::push);
    tower2 = new Stack<>();
    tower3 = new Stack<>();
  }

  /**
   * Starts moving disks.
   */
  public void move() {
    move(tower1.size(), tower1, tower3, tower2);
  }

  /**
   * Moves n disks from the origin to the destination using buffer
   * @param n the number of disks to move
   * @param origin
   * @param destination
   * @param buffer
   */
  private void move(final int n, final Stack<Integer> origin, final Stack<Integer> destination,
                    final Stack<Integer> buffer) {
    if (n == 0) {
      return;
    }
    move(n - 1, origin, buffer, destination);
    destination.push(origin.pop());
    move(n - 1, buffer, destination, origin);
  }

  /**
   * @return tower 1
   */
  public Stack<Integer> getTower1() {
    return tower1;
  }

  /**
   * @return tower 2
   */
  public Stack<Integer> getTower2() {
    return tower2;
  }

  /**
   * @return tower 3
   */
  public Stack<Integer> getTower3() {
    return tower3;
  }
}
