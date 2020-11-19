package com.sharfah.util.algorithms;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class ParenthesesTest {

  @Test
  public void test() {
    assertThat(Parentheses.generate(1), hasItems("()"));
    assertThat(Parentheses.generate(2), hasItems("()()", "(())"));
    assertThat(Parentheses.generate(3), hasItems("()()()", "((()))", "()(())", "(())()", "(()())"));
  }
}
