package com.sharfah.util.algorithms;

import static com.sharfah.util.algorithms.Permutation.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
public class PermutationTest {

  @Test
  public void testStringPermutations() {
    assertThat(permutations("a"), hasItems("a"));
    assertThat(permutations("ab"), hasItems("ab", "ba"));
    assertThat(permutations("abc"), hasItems("abc", "acb", "bac", "bca", "cab", "cba"));
  }

  @Test
  public void testListPermutations() {
    assertThat(permutations(Arrays.asList("a")), hasItems(Arrays.asList("a")));
    assertThat(permutations(Arrays.asList("a", "b", "c")), hasItems(
        Arrays.asList("a", "b", "c"),
        Arrays.asList("a", "c", "b"),
        Arrays.asList("b", "a", "c"),
        Arrays.asList("b", "c", "a"),
        Arrays.asList("c", "b", "a"),
        Arrays.asList("c", "a", "b")));
  }

}
