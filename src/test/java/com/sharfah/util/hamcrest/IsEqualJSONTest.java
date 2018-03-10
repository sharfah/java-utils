package com.sharfah.util.hamcrest;

import static com.sharfah.util.hamcrest.IsEqualJSON.equalToJSON;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IsEqualJSONTest {

  @Test
  public void test() {
    assertThat(new String[] { "foo", "bar" }, equalToJSON("[\"foo\", \"bar\"]"));
  }

  @Test(expected = AssertionError.class)
  public void testFailure() {
    assertThat(new String[] { "foo", "bar" }, equalToJSON("[\"foo2\", \"bar\"]"));
  }
}
