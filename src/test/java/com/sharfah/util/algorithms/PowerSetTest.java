package com.sharfah.util.algorithms;

import static com.sharfah.util.algorithms.PowerSet.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PowerSetTest {

  @Test
  public void testPowerSet() {

    final List<String> list = new ArrayList<> ();
    final List<List<String>> powerSet = new ArrayList<>();
    powerSet.add(Collections.emptyList());
    assertThat(powerSet(list), is(powerSet));

    list.add("a");
    powerSet.add(Arrays.asList("a"));
    assertThat(powerSet(list), is(powerSet));

    list.add("b");
    list.add("c");
    final List<List<String>> result = powerSet(list);
    assertThat(result.size(), is(8));
    assertTrue(result.contains(Collections.emptyList()));
    assertTrue(result.contains(Arrays.asList("a")));
    assertTrue(result.contains(Arrays.asList("b")));
    assertTrue(result.contains(Arrays.asList("c")));
    assertTrue(result.contains(Arrays.asList("a", "b")));
    assertTrue(result.contains(Arrays.asList("b", "c")));
    assertTrue(result.contains(Arrays.asList("a", "c")));
    assertTrue(result.contains(Arrays.asList("a", "b", "c")));
  }
  @Test
  public void testIterativePowerSet() {

    final List<String> list = new ArrayList<> ();
    final List<List<String>> powerSet = new ArrayList<>();
    powerSet.add(Collections.emptyList());
    assertThat(iterativePowerSet(list), is(powerSet));

    list.add("a");
    powerSet.add(Arrays.asList("a"));
    assertThat(iterativePowerSet(list), is(powerSet));

    list.add("b");
    list.add("c");
    final List<List<String>> result = iterativePowerSet(list);
    assertThat(result.size(), is(8));
    assertTrue(result.contains(Collections.emptyList()));
    assertTrue(result.contains(Arrays.asList("a")));
    assertTrue(result.contains(Arrays.asList("b")));
    assertTrue(result.contains(Arrays.asList("c")));
    assertTrue(result.contains(Arrays.asList("a", "b")));
    assertTrue(result.contains(Arrays.asList("b", "c")));
    assertTrue(result.contains(Arrays.asList("a", "c")));
    assertTrue(result.contains(Arrays.asList("a", "b", "c")));
  }

  @Test
  public void testIterativePowerSet2() {

    final List<String> list = new ArrayList<> ();
    final List<List<String>> powerSet = new ArrayList<>();
    powerSet.add(Collections.emptyList());
    assertThat(iterativePowerSet2(list), is(powerSet));

    list.add("a");
    powerSet.add(Arrays.asList("a"));
    assertThat(iterativePowerSet2(list), is(powerSet));

    list.add("b");
    list.add("c");
    final List<List<String>> result = iterativePowerSet2(list);
    assertThat(result.size(), is(8));
    assertTrue(result.contains(Collections.emptyList()));
    assertTrue(result.contains(Arrays.asList("a")));
    assertTrue(result.contains(Arrays.asList("b")));
    assertTrue(result.contains(Arrays.asList("c")));
    assertTrue(result.contains(Arrays.asList("a", "b")));
    assertTrue(result.contains(Arrays.asList("b", "c")));
    assertTrue(result.contains(Arrays.asList("a", "c")));
    assertTrue(result.contains(Arrays.asList("a", "b", "c")));
  }
}
