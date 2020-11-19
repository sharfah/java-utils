package com.sharfah.util.algorithms;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TowersOfHanoiTest {

  @Test
  public void test() {

    final TowersOfHanoi towersOfHanoi = new TowersOfHanoi(1, 2, 3, 4, 5);
    towersOfHanoi.move();

    assertTrue(towersOfHanoi.getTower1().isEmpty());
    assertTrue(towersOfHanoi.getTower2().isEmpty());
    assertFalse(towersOfHanoi.getTower3().isEmpty());
    assertThat(towersOfHanoi.getTower3().size(), is(5));
    assertThat(towersOfHanoi.getTower3().pop(), is(1));
    assertThat(towersOfHanoi.getTower3().pop(), is(2));
    assertThat(towersOfHanoi.getTower3().pop(), is(3));
    assertThat(towersOfHanoi.getTower3().pop(), is(4));
    assertThat(towersOfHanoi.getTower3().pop(), is(5));
  }

}
