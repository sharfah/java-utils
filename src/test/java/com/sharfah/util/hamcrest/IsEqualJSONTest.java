package com.sharfah.util.hamcrest;

import static com.sharfah.util.hamcrest.IsEqualJSON.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IsEqualJSONTest {

  @Test
  public void test() {
    assertThat(new String[] { "foo", "bar" }, equalToJSON("[\"foo\", \"bar\"]"));
  }

  @Test
  public void testFailure() {
    assertThrows(AssertionError.class, () -> {
      assertThat(new String[] { "foo", "bar" }, equalToJSON("[\"foo2\", \"bar\"]"));
    });
  }
}
